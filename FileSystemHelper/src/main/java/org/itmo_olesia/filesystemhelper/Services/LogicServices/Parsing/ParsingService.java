package org.itmo_olesia.filesystemhelper.Services.LogicServices.Parsing;

import lombok.RequiredArgsConstructor;
import org.itmo_olesia.filesystemhelper.Services.Fabric.StrategyRegisterFabric;
import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;
import org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies.IExtensionStrategy;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@RequiredArgsConstructor
@Service
public class ParsingService {
    private final NetworkService networkService;
    private final TextSegregationService textSegregationService;
    private IExtensionStrategy strategy;

    //передавай в идеале стратегию, путь лучше отдельно (чтобы стратегия был "singleton")
    public void ReferenceTracker(Path startPath, boolean flag) {
        StrategyRegisterFabric fabric = StrategyRegisterFabric.getInstance();


        try {
            Files.walkFileTree(Paths.get(String.valueOf(startPath)), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // ГЛАВНОЕ - ТУТ ФАБРИКА РЕШАЕТ, search должен вызваться конкретный по ситуации
                    if (Files.isRegularFile(file)) {
                        System.out.println(file.toAbsolutePath() + " start");
                        IExtensionStrategy strategy = fabric.FabricMethod(textSegregationService.getExtension(file));
                        strategy.setNetworkService(networkService);

                        strategy.SearchLinkInFile(file, flag);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}


