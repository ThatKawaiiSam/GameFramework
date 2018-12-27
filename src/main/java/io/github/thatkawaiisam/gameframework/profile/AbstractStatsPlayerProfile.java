package io.github.thatkawaiisam.gameframework.profile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractStatsPlayerProfile {

    private AbstractGamePlayerProfile gamePlayerProfile;

    public AbstractStatsPlayerProfile(AbstractGamePlayerProfile gamePlayerProfile) {
        this.gamePlayerProfile = gamePlayerProfile;
    }
}
