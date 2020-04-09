package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class GameTeam {

    private String id;
    private List<UUID> players = new ArrayList<>();
    private TeamState state;

    public GameTeam(String id, UUID... players) {
        this.id = id;
        this.players.addAll(
                Arrays.stream(players).collect(Collectors.toList())
        );
    }

    public GameTeam(String id, List<UUID> players) {
        this.id = id;
        this.players = players;
    }

}
