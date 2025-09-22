# Recipe: aesd-assignments
# Builds the aesdsocket binary for Assignment 6

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-dylanzflores.git;protocol=https;branch=main"
SRCREV = "e91eba79d896bc178f5a6acdd620a721efcb9776"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git/server"

bindir = "/usr/bin"

FILES:${PN} += "${bindir}/aesdsocket"

TARGET_CFLAGS = "-Wall -Wextra -g"
TARGET_LDFLAGS = "-Wl,--hash-style=gnu"

do_configure() {
    :
}

do_compile() {
    ${CC} ${TARGET_CFLAGS} ${TARGET_LDFLAGS} -o aesdsocket aesdsocket.c
}

do_install() {
    # Install the binary
    install -d ${D}${bindir}
    install -m 0755 aesdsocket ${D}${bindir}/aesdsocket
      # add startup script
    install -d ${D}/etc/init.d
    echo '#!/bin/sh' > ${D}/etc/init.d/S99aesdsocket
    echo '/usr/bin/aesdsocket &' >> ${D}/etc/init.d/S99aesdsocket
    chmod 0755 ${D}/etc/init.d/S99aesdsocket
}

