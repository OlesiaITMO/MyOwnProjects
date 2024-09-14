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


public class TXTStrategy implements IExtensionStrategy {
    @NotNull
    @Setter
    public NetworkService networkService;
    private static final TXTStrategy INSTANCE = new TXTStrategy();
    private String regex = Regexes.TotalRegex.getRegex();
    private final String  extension = String.valueOf(Extensions.TXT);

    private TXTStrategy() {}

    public static TXTStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public void SearchLinkInFile(Path file, boolean flag) {
        Pattern pattern = Pattern.compile(String.valueOf(this.regex));
        String fileName = file.getFileName().toString().toLowerCase().trim();

        if ((extension.equals(Extensions.TXT.toString()) || flag == true) && fileName.endsWith(".txt")) {
            try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
                String line;

                while ((line = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        if ((matcher.group(1) != "" && matcher.groupCount() != 0)) {
                            System.out.println("Found link: " + matcher.group() + " in file " + file + " active = " + networkService.brokenLinks(file, matcher.group()));
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
