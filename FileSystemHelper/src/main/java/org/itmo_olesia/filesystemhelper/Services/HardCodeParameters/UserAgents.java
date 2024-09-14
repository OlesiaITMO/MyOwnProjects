package org.itmo_olesia.filesystemhelper.Services.HardCodeParameters;

public enum UserAgents {
    CHROME_YANDEX_WINDOWS("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 YaBrowser/24.7.0.0 Safari/537.36"),
    CHROME_ANDROID_13("Mozilla/5.0 (Linux; Android 13; SM-G991B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116"),
    CHROME_ANDROID_12("Mozilla/5.0 (Linux; Android 12; SM-G991B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Mobile Safari/537.36"),
    CHROME_WINDOWS_116("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.97 Safari/537.36"),
    FIREFOX_WINDOWS("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:119.0) Gecko/20100101 Firefox/119.0"),
    EDGE_WINDOWS("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.96 Safari/537.36 Edg/116.0.1938.76"),
    SAFARI_MAC("Mozilla/5.0 (Macintosh; Intel Mac OS X 13_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Version/16.2 Safari/537.36"),
    CHROME_ANDROID_PIXEL("Mozilla/5.0 (Linux; Android 14; Pixel 7 XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.5993.83 Mobile Safari/537.36"),
    FIREFOX_ANDROID("Mozilla/5.0 (Android 13; Mobile; rv:117.0) Gecko/20100101 Firefox/117.0"),
    SAFARI_IPHONE("Mozilla/5.0 (iPhone; CPU iPhone OS 16_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4 Mobile/15E148 Safari/604.1"),
    SAMSUNG_BROWSER_ANDROID("Mozilla/5.0 (Linux; Android 12; SM-G991B) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/19.0 Chrome/103.0.0.0 Mobile Safari/537.36"),
    OPERA_MINI_ANDROID("Mozilla/5.0 (Android 13; Mobile; Opera Mini/68.0.3611/239.117; U; en) Presto/2.12.423 Version/12.16"),
    PUFFIN_BROWSER_IPHONE("Mozilla/5.0 (iPhone; CPU iPhone OS 15_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Puffin/9.0 Mobile/15E148 Safari/604.1"),
    YANDEX_MAC("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.97 YaBrowser/23.7.1.764 Safari/537.36"),
    BRAVE_LINUX("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.97 Safari/537.36 Brave/1.45.123");

    private final String userAgent;

    UserAgents(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return this.userAgent;
    }
}
