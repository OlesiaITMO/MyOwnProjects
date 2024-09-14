package org.itmo_olesia.filesystemhelper.Services.HardCodeParameters;

public enum Protocols {
    HTTPS("https"),
    HTTP("http"),
    FILE("file"),
    MAILTO("mailto"),
    CANBELOCAL("canBeLocal"),;


    private final String protocol;

    Protocols(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return this.protocol;
    }
}
