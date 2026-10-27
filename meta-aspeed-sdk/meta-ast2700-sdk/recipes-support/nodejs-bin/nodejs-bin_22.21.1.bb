SUMMARY = "Node.js JavaScript runtime (official linux-arm64 prebuilt)"
DESCRIPTION = "Official prebuilt node binary for aarch64, shipped as-is (only bin/node installed). glibc compat verified on target (spike A2)."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fdf4f79da4006c3b5183fddc768f1c8"

SRC_URI = "https://nodejs.org/dist/v22.21.1/node-v22.21.1-linux-arm64.tar.xz"
SRC_URI[sha256sum] = "e660365729b434af422bcd2e8e14228637ecf24a1de2cd7c916ad48f2a0521e1"

S = "${WORKDIR}/node-v22.21.1-linux-arm64"

# 官方二进制：架构与 ldflags 质检不适用；不 skip already-stripped（OE 打包会执行 strip，评审 I3 已核）
INSANE_SKIP:${PN} += "arch ldflags"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/node ${D}${bindir}/node
}

FILES:${PN} = "${bindir}/node"
