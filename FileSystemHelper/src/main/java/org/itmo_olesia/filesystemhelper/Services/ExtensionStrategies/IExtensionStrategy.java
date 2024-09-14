package org.itmo_olesia.filesystemhelper.Services.ExtensionStrategies;

import org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers.NetworkService;

import java.io.IOException;
import java.nio.file.Path;

public interface IExtensionStrategy {
    public void SearchLinkInFile(Path file, boolean flag) throws IOException;
    public void setNetworkService(NetworkService networkService);
}
