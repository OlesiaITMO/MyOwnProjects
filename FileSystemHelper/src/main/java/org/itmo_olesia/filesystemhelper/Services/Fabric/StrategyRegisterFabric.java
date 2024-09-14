package org.itmo_olesia.filesystemhelper.Services.Fabric;

import org.itmo_olesia.filesystemhelper.Services.HardCodeParameters.Extensions;
import org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies.DOCXStrategy;
import org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies.HTMLStrategy;
import org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies.IExtensionStrategy;
import org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies.TXTStrategy;

import java.util.HashMap;
import java.util.Map;


public class StrategyRegisterFabric {
    private static final StrategyRegisterFabric INSTANCE = new StrategyRegisterFabric();
    private final Map<Extensions, IExtensionStrategy> strategies = new HashMap<>();

    private StrategyRegisterFabric() {
        // Регистрация стратегий
        registerStrategy(Extensions.DOCX, DOCXStrategy.getInstance());
        registerStrategy(Extensions.TXT, TXTStrategy.getInstance());
        registerStrategy(Extensions.HTML, HTMLStrategy.getInstance());
    }
    public static StrategyRegisterFabric getInstance() {
        return INSTANCE;
    }

    public void registerStrategy(Extensions extensions, IExtensionStrategy strategySupplier) {
        strategies.put(extensions, strategySupplier);
    }

    public IExtensionStrategy FabricMethod(Extensions extensions) {
        IExtensionStrategy strategy = strategies.get(extensions);
        System.out.println("Fabric Method: " + strategy.getClass().getName());
        if (strategy != null) {
            return strategy;
        }
        throw new IllegalArgumentException("No strategy found for type: " + extensions);
    }
}

// Enum типов файлов

//
//// Пример использования
//public class Main {
//    public static void main(String[] args) {
//        StrategyFactory factory = new StrategyFactory();
//        FileProcessingStrategy strategy = factory.getStrategy(MyEnum.TYPE_A);
//        strategy.process();  // Output: Processing Type A
//    }
//}
