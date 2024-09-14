package org.itmo_olesia.filesystemhelper.Services.LogicServices.Parsing;

import lombok.RequiredArgsConstructor;
import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Extensions;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class TextSegregationService {
    public Extensions getExtension(Path file){
        String  fileName = file.getFileName().toString().toLowerCase().trim();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = "";
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            // Извлечь расширение файла
            extension = fileName.substring(dotIndex + 1);
            System.out.println("File extension: " + extension.toUpperCase());  // Output: txt
        } else {
            System.out.println("No extension found");
        }

        Extensions ext = null;
        try{
            ext = Extensions.valueOf(extension.toUpperCase());
        }catch (Exception e){
            System.out.println("Extension not found");
        }
        return ext;
    }

}
