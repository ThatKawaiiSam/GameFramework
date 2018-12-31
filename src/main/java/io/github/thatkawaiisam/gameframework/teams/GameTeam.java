package io.github.thatkawaiisam.gameframework.teams;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameTeam {

    private final int teamNumber;

    private List<GamePlayer> players = new ArrayList<>();
    private TeamState state;

    public GameTeam(int teamNumber, GamePlayer... players) {
        this.teamNumber = teamNumber;
        this.players.addAll(
                Arrays.stream(players).collect(Collectors.toList())
        );
    }

    public GameTeam(int teamNumber, List<GamePlayer> players) {
        this.teamNumber = teamNumber;
        this.players = players;
    }

}
