package me.dreamerzero.miniplaceholders.extension;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import me.dreamerzero.miniplaceholders.api.annotation.Extension;

public final class Loader {
    private Loader() {}
    public static Extension[] loadExtensions(Path extensionsPath) {
        List<Extension> extensions = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(extensionsPath, (file) -> file.toString().endsWith(".jar"))) {
            for (Path ext : stream) {

            }
        } catch(IOException e) {
            
        }
    }
}
