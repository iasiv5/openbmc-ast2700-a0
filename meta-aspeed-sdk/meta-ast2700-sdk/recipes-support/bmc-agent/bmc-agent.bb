SUMMARY = "BMC LLM agent service (pi-agent-core + pi-ai, Node.js)"
DESCRIPTION = "systemd unit + runtime dir for the on-device LLM agent. JS bundle (agent.mjs) is deployed to /var/lib/bmc-agent/ out-of-band (rwfs hot-deploy); F2 may freeze it into /usr/share/bmc-agent."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://bmc-agent.service"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "bmc-agent.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

RDEPENDS:${PN} = "nodejs-bin"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/bmc-agent.service ${D}${systemd_system_unitdir}/bmc-agent.service
}

FILES:${PN} = "${systemd_system_unitdir}/bmc-agent.service"
