package org.itmo_olesia.filesystemhelper.Services.LogicServices.Translater;

import lombok.RequiredArgsConstructor;
import org.itmo_olesia.filesystemhelper.Services.DTO.MongoDocumentDTO;
import org.springframework.stereotype.Service;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.nio.file.Files;
import java.io.File;
import static org.antlr.v4.runtime.misc.Utils.writeFile;

@RequiredArgsConstructor
@Service
public class TextHandlerService {
    // класс пока не проверен на работоспособность
    Translate translate = TranslateOptions.getDefaultInstance().getService();

    public MongoDocumentDTO translater(MongoDocumentDTO document) {
        Translation translation = translate.translate(document.getText(), Translate.TranslateOption.targetLanguage("ru"));
        document.setText(translation.getTranslatedText());

        String filePath = document.getOriginalWay();
        try {
            File file = new File(filePath);

            if (file.exists() && !file.isDirectory() && Files.isReadable(file.toPath())) {
                String text = file.toString();
                Translation translationOfWay = translate.translate(text, Translate.TranslateOption.targetLanguage("ru"));
                String translatedText = translationOfWay.getTranslatedText();
                writeFile(filePath, translatedText);
                System.out.println("Файл успешно переведен и перезаписан!");
            }
            else {
                System.out.println("Файл не существует по указанному пути: " + filePath);
                System.out.println("Locally could not find, but db object was translated!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Locally could not find, but db object was translated!");
        }

        return document;
    }


}
