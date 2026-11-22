require openjdk-25-target.inc

SUMMARY = "Eclipse Temurin 25 Java Runtime Environment"
DESCRIPTION = "Prebuilt Eclipse Temurin OpenJDK 25 JRE from Adoptium for target systems."
SECTION = "libs"

API_IMAGE_TYPE = "jre"

# https://adoptium.net/temurin/releases
JVM_CHECKSUM:aarch64 = "963931723891302ebb2687178532c3d793d71e2577cd058061c7efb396140a86"
JVM_CHECKSUM:x86-64 = "6fac98f0c8202efc3135a9e9e42aa61963ec42d1c5cbb40caf231e7b14a24ef6"
JVM_CHECKSUM:riscv64 = "224c72d235b0b9ae49e642ae75ade8d8ed69ff693b9ae2fa7cef8e0e48760289"

# https://github.com/paketo-buildpacks/adoptium/blob/main/buildpack.toml#L292
JVM_SRC_CHECKSUM = "f331d870d998f9c144c361c220960f8ad2de169dc1b1c4dc4145291c501c2341"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
