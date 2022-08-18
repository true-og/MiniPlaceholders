package me.dreamerzero.miniplaceholders.api.annotation.processor;

import me.dreamerzero.miniplaceholders.api.enums.Platform;

public final class ProcessedExtension {
    private final String name;
    private final String version;
    private final Platform[] platforms;
    private final String clazz;

    ProcessedExtension(String name, String version, String clazz, Platform... platforms) {
        this.name = name;
        this.version = version;
        this.platforms = platforms;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
    public Platform[] getPlatforms() {
        return platforms;
    }
    public String getClazz() {
        return this.clazz;
    }
}
