package br.com.juliocnsouza.rachamanager.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.juliocnsouza.rachamanager.entity.Player;

public class GenerateTeam {

    private final Set<Player> selectedPlayers;
    private List<Player> copyPlayers;
    private final int teamsQtd;
    private int numberOfTeams;

    public GenerateTeam(final Set<Player> selectedPlayers, final int teamsQtd) {

        this.selectedPlayers = selectedPlayers;
        this.teamsQtd = teamsQtd;

    }

    private void initCopy() {
        this.copyPlayers = new ArrayList<Player>();
        for (final Player p : this.selectedPlayers) {
            this.copyPlayers.add(new Player());
        }
        Collections.copy(this.copyPlayers, new ArrayList<Player>(this.selectedPlayers));
    }

    public String getTeams() {
        this.initCopy();
        if ((this.selectedPlayers != null) && !this.selectedPlayers.isEmpty()
                        && (this.teamsQtd > 0)) {
            this.numberOfTeams = this.selectedPlayers.size() / this.teamsQtd;
            if ((this.selectedPlayers.size() % this.teamsQtd) > 0) {
                this.numberOfTeams++;
            }
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.numberOfTeams; i++) {
                sb.append(this.getTeam(i + 1));
            }
            return sb.toString();
        }
        return null;
    }

    private String getTeam(final int team) {
        final StringBuilder sb = new StringBuilder();
        if (team > 1) {
            sb.append("\n\n");
        }
        sb.append("Time " + team + ":\n");
        int i = 0;
        while (i < this.teamsQtd) {

            if (!this.copyPlayers.isEmpty()) {
                final int nextInt = new Random().nextInt(this.copyPlayers.size());
                final String name = this.copyPlayers.get(nextInt).getNome().toUpperCase();
                final String value = " - " + name;
                sb.append(value);
                this.copyPlayers.remove(nextInt);

            } else {
                sb.append(" - (sem jogador)");
            }
            if (i < (this.teamsQtd - 1)) {
                sb.append("\n");
            }

            i++;

        }
        return sb.toString();
    }


}
