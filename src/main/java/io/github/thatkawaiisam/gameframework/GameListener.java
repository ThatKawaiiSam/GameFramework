package io.github.thatkawaiisam.gameframework;

import io.github.thatkawaiisam.plugintemplate.bukkit.BukkitListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class GameListener<T> extends BukkitListener {

    private T game;

    public GameListener(JavaPlugin javaPlugin, T game) {
        super(javaPlugin);
        this.game = game;
    }
}
