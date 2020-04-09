package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.plugintemplate.bukkit.BukkitModule;
import io.github.thatkawaiisam.plugintemplate.bukkit.BukkitModuleListener;
import lombok.Getter;

@Getter
public class GameModuleListener<T extends BukkitModule> extends BukkitModuleListener {

    private AbstractGame game;

    public GameModuleListener(T module, AbstractGame game) {
        super(module);
        this.game = game;
    }
}
