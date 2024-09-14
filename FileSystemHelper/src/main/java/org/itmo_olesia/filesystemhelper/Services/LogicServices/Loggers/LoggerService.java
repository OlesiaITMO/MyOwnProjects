package org.itmo_olesia.filesystemhelper.Services.LogicServices.Loggers;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoggerService {
    // Пока не проверен

    private static final Logger logger = LogManager.getLogger(NetworkService.class);

    public void brokenLink(String url) {
        logger.info("Найдена битая ссылка: {}", url);
    }

    public void fixedBrokenLink(String url, String urlReplace) {
        logger.info("Битая ссылка {} заменена на {}", url, urlReplace);
    }
}
