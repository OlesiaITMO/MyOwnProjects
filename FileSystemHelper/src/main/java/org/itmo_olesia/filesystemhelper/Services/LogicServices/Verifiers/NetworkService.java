package org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers;

import lombok.RequiredArgsConstructor;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.UserAgents;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.net.URL;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@Service
public class NetworkService {
    private final EmailVerifierService emailVerifierService;

    public boolean brokenLinks(Path filePath, String way) throws IOException {
            // Проверка протокола URL
            System.out.println("Before : " + way);
            URL url = null;
            String protocol;
            try {
                url = URI.create(way).toURL();
                protocol = url.getProtocol();
            } catch (Exception e) {
                protocol = "CanBeLocal"; // сюда попал ещё и якорь
            }
            System.out.println(protocol);

            //тут вызов стратегии через фабрику


            if (protocol.equals("file")) {
                File file = new File(url.getPath());
                boolean flag = file.exists() && !file.isDirectory() && Files.isReadable(file.toPath());
                if (!flag) {
                    file = new File(filePath.getParent() + way);
                    flag = file.exists() && !file.isDirectory() && Files.isReadable(file.toPath());
                }
                return flag;
            }
            // либо ошибка, либо локальный, либо якорь
            if (protocol.equals("CanBeLocal")) {
                if (way.startsWith("#")) { // Дополни, что должно быть id="" или name=""
                    System.out.println("After : " + way);
                    String regexId = "id=\"" + way.toString().substring(1) + "\"";
                    String regexName = "name=\"" + way.toString().substring(1) + "\"";
                    Pattern patternId = Pattern.compile(regexId); //Pattern.CASE_INSENSITIVE - Тогда не был бы чувствителен к регистру
                    Pattern patternName = Pattern.compile(regexName);

                    try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            Matcher matcherId = patternId.matcher(line);
                            Matcher matcherName = patternName.matcher(line);
                            if (matcherId.find() || matcherName.find()) {
                                return true;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    File file = new File(way); // если это не неккорректный путь, а локальный, но уже удалённый файл, просто будет false
                    boolean flag = file.exists() && !file.isDirectory() && Files.isReadable(file.toPath());
                    if (!flag) {
                        file = new File(filePath.getParent() + "\\" + way.toString().replace("/", "\\"));
                        file.getPath().toString().replace("/", "\\");
                        System.out.println("!!!! " + file.toPath());
                        flag = file.exists() && !file.isDirectory() && Files.isReadable(file.toPath());
                        return flag;
                    }
                } catch (Exception e) { // всё же некорректный путь
                    System.out.println(way + " - incorrect url");
                }
            } else if (protocol.equals("http") || protocol.equals("https")) {
                int responseCode = 200;
                boolean success = false;
                int countOfAttempts = 0;

                for (UserAgents userAgent : UserAgents.values()) {
                    // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("14.204.150.66", 8080));

                    HttpURLConnection connection = null;
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                    } catch (IOException e) {
                        System.out.println("Long connection");
                    }
                    try {
                        connection.setRequestMethod("HEAD");
                    } catch (ProtocolException e) {
                        System.out.println("Can't set header");
                    }
                    connection.setConnectTimeout(12000);
                    connection.setReadTimeout(12000);

                    // Устанавливаем заголовки
                    connection.setRequestProperty("User-Agent", userAgent.getUserAgent());
                    connection.setRequestProperty("Connection", "keep-alive"); // important
                    connection.setRequestProperty("Cache-Control", "no-store"); // important
                    Random random = new Random();
                    String encodedCredentials = Base64.getEncoder().encodeToString((random.longs().toString() + ":" + random.longs().toString()).getBytes());
                    connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
                    connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

                    try {
                        responseCode = connection.getResponseCode();
                    } catch (IOException e) {
                        System.out.println("Long connection");
                    }

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        success = true;
                        System.out.println("Response Code: " + responseCode + " " + "CountOfAttepts: " + countOfAttempts);
                        //System.out.println("Header Fields: " + connection.getHeaderFields());
                        break;
                    } else {
                        countOfAttempts++;
                        //System.out.println(countOfAttempts);
                        System.out.println("Attempt with User-Agent: \"" + userAgent.getUserAgent() + "\" failed. Response Code: " + responseCode);
                    }
                }

                if (!success) {
                    if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                        System.out.println("Needs to be checked, 403");
                    }
                    System.out.println("All attempts failed.");
                }

                return success;
            }
            // обработка якоря
            else if (protocol.equals("mailto")) { // тут должно быть для якоря + для почты я бы сделала всё же проверку
                emailVerifierService.verifyEmail(way);
            } else {
                System.out.println("Протокол " + protocol + " не поддерживается.");
            }
            return false;
    }
}
