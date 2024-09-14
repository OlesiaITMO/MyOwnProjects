package org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies;

import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Extensions;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Regexes;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTMLStrategy implements IExtensionStrategy {
    @NotNull
    @Setter
    public NetworkService networkService;
    private static final HTMLStrategy INSTANCE = new HTMLStrategy();
    private String regex = Regexes.HTMLRegex.getRegex();
    private String extension = String.valueOf(Extensions.HTML);

    private HTMLStrategy() {}

    public static HTMLStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public void SearchLinkInFile(Path file, boolean flag) {
        Pattern pattern = Pattern.compile(String.valueOf(this.regex));
        String fileName = file.getFileName().toString().toLowerCase().trim();

        if ((extension == Extensions.HTML.toString() || flag == true) && fileName.endsWith(".html")) {
            try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
                String line;
                // Чтение файла построчно
                while ((line = br.readLine()) != null) {
                    // Применение регулярного выражения к каждой строке
                    Matcher matcher = pattern.matcher(line);
                    //System.out.println(line);
                    // Поиск совпадений
                    while (matcher.find()) {
                        if ((matcher.group(1) != "" && matcher.groupCount() != 0)) {
                            System.out.println("Found link: " + matcher.group(1) + " in file " + file + " active = " + networkService.brokenLinks(file, matcher.group(1)));
                        } else {
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
