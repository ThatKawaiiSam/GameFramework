package io.github.thatkawaiisam.gameframework.votes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class VoteEntry {

    private final UUID uuid;
    private VoteOption option;
    private int voteWeight;

    public VoteEntry(UUID uuid, int voteWeight, VoteOption voteOption) {
        this.uuid = uuid;
        this.voteWeight = voteWeight;
        this.option = voteOption;
    }

    public VoteEntry(Player player, int voteWeight, VoteOption voteOption) {
        this(player.getUniqueId(), voteWeight, voteOption);
    }
}
