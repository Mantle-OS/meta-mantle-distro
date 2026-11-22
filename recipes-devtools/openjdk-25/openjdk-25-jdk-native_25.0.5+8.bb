require openjdk-25-native.inc

SUMMARY = "Eclipse Temurin 25 Java Development Kit"
DESCRIPTION = "Prebuilt Eclipse Temurin OpenJDK 25 JDK from Adoptium for native builds."
SECTION = "devel"


API_IMAGE_TYPE = "jdk"
# https://adoptium.net/temurin/releases
JVM_CHECKSUM:aarch64 = "bb5b2ded252f21c6090cf780b0a408ad0d83bbb557e702955643557f13231880"
JVM_CHECKSUM:x86-64 = "68982529869257d0ee6b824ec5aa270ff8b118bb438670da0b97ebdab178d7fb"
JVM_CHECKSUM:riscv64 = "21d9ee84f64d845e7de888f86cc2abe3256c75853a0d5abe7fd42d6526374e90"
# https://github.com/paketo-buildpacks/adoptium/blob/main/buildpack.toml#L276
JVM_SRC_CHECKSUM = "f331d870d998f9c144c361c220960f8ad2de169dc1b1c4dc4145291c501c2341"
