package org.itmo_olesia.filesystemhelper.Services.HardCodeParameters;

public enum Regexes {
    HTMLRegex("href=\\\"(.*?)\\\""),
    TotalRegex("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    // в данном случае group(1) выхватывает протокол, ранее она отбрасывала href, теперь в принципе берёт просто протокол


    private final String regex;

    Regexes(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return this.regex;
    }
}
