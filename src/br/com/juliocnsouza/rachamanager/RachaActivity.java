package br.com.juliocnsouza.rachamanager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.juliocnsouza.rachamanager.database.GenericDAO;
import br.com.juliocnsouza.rachamanager.entity.Player;
import br.com.juliocnsouza.rachamanager.util.AlertBuilder;
import br.com.juliocnsouza.rachamanager.util.CollectionsUtil;
import br.com.juliocnsouza.rachamanager.util.GenerateTeam;

public class RachaActivity extends Activity {

    private Set<Player> allPlayers;
    private Set<Player> selectedPlayers;
    private TextView tvSelectedPlayers;
    private TextView tvTeamSize;
    private TextView tvShowTeams;
    private Button btGenerate;

    private void initAllPlayers() {
        final GenericDAO<Player, String> playersDao =
                        new GenericDAO<Player, String>(this, Player.class);
        try {
            this.allPlayers = new TreeSet<Player>(playersDao.callDAO().queryForAll());
        } catch (final SQLException e) {
            e.printStackTrace();
            AlertBuilder.showSimpleDialog(this, "Erro ao buscar Jogadores", e.getMessage(), "OK");
        }
    }

    private void init() {
        this.initAllPlayers();
        this.selectedPlayers = new TreeSet<Player>();
        this.tvSelectedPlayers = (TextView) this.findViewById(R.id.tvRachaPlayers);
        this.tvTeamSize = (TextView) this.findViewById(R.id.tvTeamSize);
        this.tvShowTeams = (TextView) this.findViewById(R.id.tvRachaTeams);
        this.btGenerate = (Button) this.findViewById(R.id.btGenerateRacha);
        this.addListeners();
    }

    private void addListeners() {
        this.setOnClickListenerToTextViewMultiChoice(this.tvSelectedPlayers,
                        "Selecione os Jogadores do Racha");
        this.setOnClickListenerToTextViewSingleChoice(this.tvTeamSize,
                        "Selecione a quantidade de jogadores por time",
                        Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
        this.btGenerate.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(final View arg0) {
                if (this.hasValues()) {
                    RachaActivity.this.tvShowTeams.setText(new GenerateTeam(
                                    RachaActivity.this.selectedPlayers, Integer
                                                    .parseInt(RachaActivity.this.tvTeamSize
                                                                    .getText().toString()))
                                    .getTeams());
                } else {
                    AlertBuilder.showSimpleDialog(
                                    RachaActivity.this,
                                    "Racha Não Gerado",
                                    "Verifique se os jogadores e tamanho do time foram selecionados",
                                    "OK");
                }

            }

            private boolean hasValues() {
                if ((RachaActivity.this.tvSelectedPlayers.getText() != null)
                                && (RachaActivity.this.tvSelectedPlayers.getText().length() > 0)
                                && (RachaActivity.this.tvTeamSize.getText() != null)
                                && (RachaActivity.this.tvTeamSize.getText().length() > 0)) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_racha);
        this.init();
    }

    @Override
    public void onBackPressed() {
        final Intent gotoMainMenu = new Intent(RachaActivity.this, MainMenuActivity.class);
        this.startActivity(gotoMainMenu);
        this.overridePendingTransition(R.anim.push_side_in, R.anim.push_side_out);
        this.finish();
    }

    private void setOnClickListenerToTextViewMultiChoice(final TextView tv, final String title) {
        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(RachaActivity.this);
                builder.setTitle(title);
                final List<Player> copyForOptions =
                                new ArrayList<Player>(RachaActivity.this.allPlayers);
                Collections.sort(copyForOptions);
                final String[] opcoes = CollectionsUtil.getArray(copyForOptions);
                final boolean[] selectedItens = new boolean[opcoes.length];

                // initialize values
                for (int i = 0; i < opcoes.length; i++) {
                    if ((RachaActivity.this.selectedPlayers != null)
                                    && !RachaActivity.this.selectedPlayers.isEmpty()) {
                        for (final Player player : RachaActivity.this.selectedPlayers) {
                            if ((player != null) && player.getNome().equals(opcoes[i])) {
                                selectedItens[i] = true;
                            }
                        }
                    }
                }
                builder.setMultiChoiceItems(opcoes, selectedItens,
                                new DialogInterface.OnMultiChoiceClickListener() {

                                    @Override
                                    public void onClick(final DialogInterface dialog,
                                                    final int which, final boolean isChecked) {
                                        if (isChecked) {
                                            RachaActivity.this.selectedPlayers
                                                            .add(new ArrayList<Player>(
                                                                            RachaActivity.this.allPlayers)
                                                                            .get(which));
                                        } else {
                                            RachaActivity.this.selectedPlayers
                                                            .remove(new ArrayList<Player>(
                                                                            RachaActivity.this.allPlayers)
                                                                            .get(which));
                                        }

                                    }
                                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        final StringBuilder sb = new StringBuilder();
                        if ((RachaActivity.this.selectedPlayers != null)
                                        && !RachaActivity.this.selectedPlayers.isEmpty()) {
                            int count = 0;
                            for (final Player players : RachaActivity.this.selectedPlayers) {
                                count++;
                                sb.append(players.getNome());
                                if (count < RachaActivity.this.selectedPlayers.size()) {
                                    sb.append(", ");
                                }
                            }
                        }
                        RachaActivity.this.tvSelectedPlayers.setText("");
                        if (sb.toString() != null) {
                            RachaActivity.this.tvSelectedPlayers.setText(sb.toString());
                        }
                        dialog.dismiss();
                    }
                });


                AlertBuilder.mostraDialog(builder.create(), true);
            }
        });
    }


    private void setOnClickListenerToTextViewSingleChoice(final TextView tv, final String title,
                    final List<String> options) {
        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(RachaActivity.this);
                builder.setTitle(title);
                final String[] opcoes = CollectionsUtil.getArrayStr(options);
                builder.setSingleChoiceItems(opcoes, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        tv.setText(opcoes[which]);
                        dialog.dismiss();
                    }
                });
                AlertBuilder.mostraDialog(builder.create(), true);
            }
        });
    }

}
