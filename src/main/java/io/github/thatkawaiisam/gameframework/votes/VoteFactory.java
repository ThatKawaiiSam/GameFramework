package io.github.thatkawaiisam.gameframework.votes;

import io.github.thatkawaiisam.utils.MessageUtility;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.*;

@Getter @Setter
public class VoteFactory {

    private List<VoteOption> voteOptions = new ArrayList<>();
    private List<VoteEntry> voteEntries = new ArrayList<>();

    public void clearVotes() {
        voteEntries.clear();
    }

    public VoteOption getWinningVoteOutright() {
        final List<VoteOption> avaliable = getVoteOptions();
        boolean random = false;
        if (avaliable.size() < 1) {
            random = true;
        }
        List<VoteOption> finalOptions = new ArrayList<>();
        int buffer = 0;
        for (VoteOption loopOption : avaliable){
            int amount = getVotesOfOption(loopOption, true);
            if (amount > buffer) {
                finalOptions.clear();
                finalOptions.add(loopOption);
                buffer = amount;
            }
            if (amount == buffer) {
                finalOptions.add(loopOption);
                buffer = amount;
            }
        }
        if (finalOptions.size() > 1 || random){
            return finalOptions.get(new Random().nextInt(finalOptions.size()));
        } else {
            return finalOptions.get(0);
        }
    }


    public VoteOption getWinningVoteChance() {
        final List<VoteOption> cachedVoteOptions = getVoteOptions();
        List<VoteOption> buffer = new ArrayList<>();
        for (VoteOption loopVoteOption : cachedVoteOptions) {
            for (int i = 0; i <= Math.round(getChanceOfVoteOption(loopVoteOption, true)); i++) {
                buffer.add(loopVoteOption);
            }
        }
        Collections.shuffle(buffer);
        return buffer.get(new Random().nextInt(buffer.size()-1));
    }

    public float getChanceOfVoteOption(VoteOption voteOption, boolean withWeights) {
        int totalVotes = getTotalVotes(withWeights);
        int optionVotes = getVotesOfOption(voteOption, withWeights);
        return ((float) optionVotes / totalVotes * 100);
    }

    public int getVotesOfOption(VoteOption voteOption, boolean withWeights) {
        int buffer = 0;
        for (VoteEntry loopVoteEntry : voteEntries) {
            if (loopVoteEntry.getOption() == voteOption) {
                if (withWeights) {
                    buffer += loopVoteEntry.getVoteWeight();
                } else {
                    buffer += 1;
                }
            }
        }
        return buffer;
    }

    public int getTotalVotes(boolean withWeights) {
        int buffer = 0;
        for (VoteEntry loopVoteEntry : voteEntries) {
            if (withWeights) {
                buffer += loopVoteEntry.getVoteWeight();
            } else {
                buffer += 1;
            }
        }
        return buffer;
    }

    public VoteOption getVoteOption(String id) {
        for (VoteOption voteOption : voteOptions) {
            if (voteOption.getId().equalsIgnoreCase(id)) {
                return voteOption;
            }
            for (String alias : voteOption.getAliases()) {
                if (id.equalsIgnoreCase(alias)) {
                    return voteOption;
                }
            }
        }
        return null;
    }

    public boolean hasPlayerVoted(Player player) {
        return hasPlayerVoted(player.getUniqueId());
    }
    public boolean hasPlayerVoted(UUID uuid) {
        for (VoteEntry loopVoteEntry : voteEntries) {
            if (loopVoteEntry.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void addVote(Player player, String option, int weight) {
        VoteOption voteOption = getVoteOption(option);

        if (voteOption == null) {
            player.sendMessage(MessageUtility.formatMessage("&cCould not find vote option."));
            return;
        }

        if (hasPlayerVoted(player)) {
            player.sendMessage(MessageUtility.formatMessage("&cYou can only vote once."));
            return;
        }

        getVoteEntries().add(new VoteEntry(player.getUniqueId(), weight, voteOption));
        player.sendMessage(MessageUtility.formatMessage("&aYou have successfully voted for " + voteOption.getId()));
    }

}
