package org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies;

import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Extensions;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Regexes;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DOCXStrategy implements IExtensionStrategy {
    @NotNull
    @Setter
    public NetworkService networkService;
    private String regex = Regexes.TotalRegex.getRegex();
    private String extension = String.valueOf(Extensions.DOCX);

    private static final DOCXStrategy INSTANCE = new DOCXStrategy();

    private DOCXStrategy() {}

    public static DOCXStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public void SearchLinkInFile(Path file, boolean flag) throws IOException {
        Pattern pattern = Pattern.compile(this.regex);
        String fileName = file.getFileName().toString().toLowerCase().trim();
        if ((extension.equals(Extensions.DOCX.toString()) || flag == true) && fileName.endsWith(".docx")) {
            System.out.println("WORD search " + fileName);
            System.out.println(file);
            FileInputStream fis = new FileInputStream(file.toFile());
            XWPFDocument doc = new XWPFDocument(fis); // кидает IOEx

            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                Matcher matcherW = pattern.matcher(paragraph.getText());
                System.out.println(paragraph.getText());
                while (matcherW.find()) {
                    System.out.println("W start");
                    if ((matcherW.group(1) != "" && matcherW.groupCount() != 0)) {
                        System.out.println("Found link: " + matcherW.group() + " in file " + file + " active = " + networkService.brokenLinks(file, matcherW.group()));
                    }
                    System.out.println();
                }
            }
        }

    }
}
