package io.github.thatkawaiisam.gameframework.votes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class VoteOption {

    private String id;
    private String[] aliases;

}
