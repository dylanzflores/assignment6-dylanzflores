LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-dylanzflores.git;protocol=https;branch=main \
           file://aesdsocket.init"
SRCREV = "e91eba79d896bc178f5a6acdd620a721efcb9776"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git/server"

bindir = "/usr/bin"

FILES:${PN} += "${bindir}/aesdsocket \
                ${sysconfdir}/init.d/aesdsocket \
                ${sysconfdir}/rcS.d/S99aesdsocket"

TARGET_CFLAGS = "-Wall -Wextra -g"
TARGET_LDFLAGS = "-Wl,--hash-style=gnu"

do_configure() {
    :
}

do_compile() {
    ${CC} ${TARGET_CFLAGS} ${TARGET_LDFLAGS} -o aesdsocket aesdsocket.c
}

do_install() {
    # Binary
    install -d ${D}${bindir}
    install -m 0755 aesdsocket ${D}${bindir}/aesdsocket

    # Init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket.init ${D}${sysconfdir}/init.d/aesdsocket

    # Runlevel symlink so it starts on boot
    install -d ${D}${sysconfdir}/rcS.d
    ln -sf ../init.d/aesdsocket ${D}${sysconfdir}/rcS.d/S99aesdsocket
}

