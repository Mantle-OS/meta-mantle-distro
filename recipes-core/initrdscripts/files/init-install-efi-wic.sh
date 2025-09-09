#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

target=""
wic_file=""

# Look for rootfs.img on all mounted iso9660 volumes
find_wic_file(){
    sleep 1
    iso_dir=$(awk '$3 == "iso9660" {print $2}' /proc/mounts)
    echo "Checking $mnt for rootfs.img..."
    if [ -f "$iso_dir/rootfs.img" ]; then
        wic_file="$iso_dir/rootfs.img"
        echo "Found WIC image: $wic_file"
    fi

    if [ -z "$wic_file" ]; then
        echo "ERROR: Could not find rootfs.img on any ISO9660 mount. Aborting."
        exit 1
    fi
}

get_devices(){
    sleep 1
    dev_info="Unknown"
    dev_size="?"
    printf "\033c"
    echo "===================-===================-===================-==================="
    echo "                              Available drives:"
    echo "===================-===================-===================-==================="


    for dev in $(ls /sys/block); do
        [ -e "/sys/block/$dev/device" ] || continue

        case $dev in
            loop*|ram*|sr*|fd*)
                ;;
            *)

                if [ -e "/sys/block/$dev/device/inquiry" ]; then
                    dev_info="$(cat /sys/block/$dev/device/inquiry 2>/dev/null)"
                elif [ -e "/sys/block/$dev/device/model" ]; then
                    dev_info="$(cat /sys/block/$dev/device/model 2>/dev/null)"
                fi
                size_sectors=$(cat /sys/block/$dev/size 2>/dev/null)
                if [ -n "$size_sectors" ]; then
                    dev_size=$((size_sectors / 2048))
                fi
                echo " - $dev : Model: $dev_info | Size: $dev_size (MB)"
                ;;
        esac
    done
    echo "===================-===================-===================-==================="
    echo
    echo "              Enter target device name (e.g., sda) or 'c' to cancel:           "
    read answer
    if [ "$answer" = "c" ]; then
        echo "Installation aborted."
        exit 1
    fi
    target="/dev/$answer"
    if [ ! -b "$target" ]; then
        echo "Invalid device. Installation aborted."
        exit 1
    fi
}

flash(){
    rm -f /etc/udev/rules.d/automount.rules
    rm -f /etc/udev/scripts/mount*
    printf "\033c"
    echo "===================-===================-===================-==================="
    echo "                        Flashing Target please wait"
    echo "===================-===================-===================-==================="
    umount ${device}* 2> /dev/null || /bin/true
    echo "Writing ${wic_file} to ${target}..."
    dd if="${wic_file}" of="${target}" bs=4M &
    pid=$!

    spinner_len=4
    i=0
    spinner=".oO@"

    while kill -0 "$pid" 2>/dev/null; do
        char=$(printf "%s" "$spinner" | cut -c $(( (i % spinner_len) + 1 )) )
        printf "\r[ %s ] Flashing in progress..." "$char"
        i=$((i + 1))
        sleep 0.5
    done

    sync
    printf "\rFlash complete.\n"
}

find_wic_file
get_devices

printf "\033c"
echo "===================-===================-===================-==================="
echo "                      !!!!   WARNING !!!!!"
echo "      You picked $answer This will destroy whatever data is on this disk."
echo "      Press Y or y to continue Or n to re-scan for drives"
echo "===================-===================-===================-==================="
read accept
case "$accept" in
    y|Y)
        flash
        ;;
    *)
        get_devices
        ;;
esac
