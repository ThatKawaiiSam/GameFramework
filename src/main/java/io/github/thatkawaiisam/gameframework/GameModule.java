package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.plugintemplate.bukkit.BukkitModule;
import io.github.thatkawaiisam.plugintemplate.shared.IListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class GameModule<T> extends BukkitModule {

    private T game;

    public GameModule(String moduleName, JavaPlugin javaPlugin, boolean generateConfiguration, T game) {
        super(moduleName, javaPlugin, generateConfiguration);
        this.game = game;
    }

    @Override
    public void enable() {
        if (this.generateConfiguration) {
            this.configuration.loadFile();
        }

        this.onEnable();
        this.listeners.forEach(IListener::register);
        this.enabled = true;
    }

    @Override
    public void disable() {
        this.listeners.forEach(IListener::unregister);
        this.listeners.clear();
        this.onDisable();
        if (this.generateConfiguration) {
            this.configuration.saveFile();
        }

        this.enabled = false;
    }


}
