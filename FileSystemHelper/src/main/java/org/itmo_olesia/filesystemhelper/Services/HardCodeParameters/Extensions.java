package org.itmo_olesia.filesystemhelper.Services.HardCodeParameters;

public enum Extensions {
    XML(".xml"),
    HTML(".html"),
    TXT(".txt"),
    DOC(".doc"),
    DOCX(".docx"),
    PDF(".pdf"),
    CSS(".css"),
    JS(".js"),
    ALL(".all"),
    JSON(".json");

    private final String extension;

    Extensions(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }
}
