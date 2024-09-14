package org.itmo_olesia.filesystemhelper.ControllersAndPush;

import lombok.RequiredArgsConstructor;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Parsing.TextSegregationService;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Parsing.ParsingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Component
public class Push {
    private final NetworkService networkService;
    private final TextSegregationService textSegregationService;
    @Scheduled(cron = "0 * * * * *")
    //@Scheduled(cron = "0 */5 * * * *")
    public void startOfWork() throws IOException {
        // сюда нужно перенести основные параметры из main, здесь определяются
        // директории для прохода и тд


        ParsingService parsingService = new ParsingService(networkService, textSegregationService);
        Path startPath = Paths.get("C:\\Users\\Olesia\\Desktop\\MyHTML");
        Path hateHimPath = Paths.get("C:\\Users\\Olesia\\Desktop\\AllTypes");
        //parsingService.htmlReferenceTracker(startPath, Regexes.HTMLRegex.getRegex());
        //parsingService.htmlReferenceTracker(hateHimPath, Regexes.TotalRegex.getRegex());
        parsingService.ReferenceTracker(hateHimPath, true);
        //parsingService.htmlReferenceTracker(hateHimPath, "тык");
    }

}
