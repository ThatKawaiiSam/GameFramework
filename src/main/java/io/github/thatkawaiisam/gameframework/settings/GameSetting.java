package io.github.thatkawaiisam.gameframework.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class GameSetting<T> {

    private String id;

    private T value;

    public void set(T t) {
        this.value = t;
    }

}
