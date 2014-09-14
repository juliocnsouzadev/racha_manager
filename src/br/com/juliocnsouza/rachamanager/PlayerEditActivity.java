package br.com.juliocnsouza.rachamanager;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import br.com.juliocnsouza.rachamanager.database.GenericDAO;
import br.com.juliocnsouza.rachamanager.entity.Player;
import br.com.juliocnsouza.rachamanager.util.AlertBuilder;
import br.com.juliocnsouza.rachamanager.util.BundleBuilder;
import br.com.juliocnsouza.rachamanager.util.RecoverBundle;

import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;

public class PlayerEditActivity extends Activity {

    private Player player;
    private EditText etNome;
    private RatingBar rbGoalKeeperSkills;
    private RatingBar rbDefenseSkills;
    private RatingBar rbAttackSkills;
    private Button btSave;
    private boolean isUpdate;
    private String oldKey;

    private void init() {

        this.etNome = (EditText) this.findViewById(R.id.etPlayerName);
        this.rbAttackSkills = (RatingBar) this.findViewById(R.id.rbAttackSkills);
        this.rbDefenseSkills = (RatingBar) this.findViewById(R.id.rbDefenserSkills);
        this.rbGoalKeeperSkills = (RatingBar) this.findViewById(R.id.rbGoalKeeperSkills);
        this.btSave = (Button) this.findViewById(R.id.btConfirmPlayer);

        final RecoverBundle recoverBundle = RecoverBundle.getInstance();
        this.player =
                        (Player) recoverBundle.getSerializable(this,
                                        BundleBuilder.Key.SELECTED_PLAYER);
        if (this.player == null) {
            this.player = new Player();
        } else {
            this.etNome.setText(this.player.getNome());
            this.rbAttackSkills.setRating(Float.valueOf(this.player.getAttackSkills()));
            this.rbDefenseSkills.setRating(Float.valueOf(this.player.getDefenseSkills()));
            this.rbGoalKeeperSkills.setRating(Float.valueOf(this.player.getGoalKeeperSkills()));
            this.isUpdate = true;
            this.oldKey = this.player.getNome();
        }
        this.addListeners();
    }

    private void addListeners() {
        this.rbAttackSkills.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating,
                            final boolean fromUser) {
                PlayerEditActivity.this.player.setAttackSkills(rating);
            }
        });

        this.rbDefenseSkills.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating,
                            final boolean fromUser) {
                PlayerEditActivity.this.player.setDefenseSkills(rating);
            }
        });

        this.rbGoalKeeperSkills.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating,
                            final boolean fromUser) {
                PlayerEditActivity.this.player.setGoalKeeperSkills(rating);
            }
        });

        this.btSave.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (this.hasValues()) {
                    PlayerEditActivity.this.player.setNome(PlayerEditActivity.this.etNome.getText()
                                    .toString());
                    this.save();
                } else {
                    AlertBuilder.showSimpleDialog(PlayerEditActivity.this, "Dados Inválidos",
                                    "O jogador precisa de um nome", "OK");
                }

            }

            private void save() {
                final GenericDAO<Player, String> dao =
                                new GenericDAO<Player, String>(PlayerEditActivity.this,
                                                Player.class);

                try {
                    if (PlayerEditActivity.this.isUpdate
                                    && !PlayerEditActivity.this.player.getNome().equals(
                                                    PlayerEditActivity.this.oldKey)) {
                        dao.callDAO().deleteById(PlayerEditActivity.this.oldKey);
                    }
                    final CreateOrUpdateStatus createOrUpdate =
                                    dao.callDAO().createOrUpdate(PlayerEditActivity.this.player);
                    if (createOrUpdate.isCreated() || createOrUpdate.isUpdated()) {
                        AlertBuilder.showSimpleDialog(PlayerEditActivity.this, "Mensagem",
                                        "Jogador Salvo com Sucesso!", "OK");
                        final Intent gotoPlayers =
                                        new Intent(PlayerEditActivity.this, PlayersActivity.class);
                        PlayerEditActivity.this.startActivity(gotoPlayers);
                        PlayerEditActivity.this.overridePendingTransition(R.anim.push_side_in,
                                        R.anim.push_side_out);
                        PlayerEditActivity.this.finish();

                    } else {
                        AlertBuilder.showSimpleDialog(PlayerEditActivity.this, "Mensagem",
                                        "Jogador Não foi Salvo", "OK");
                    }
                } catch (final SQLException e) {
                    AlertBuilder.showSimpleDialog(PlayerEditActivity.this, "Mensagem",
                                    "Jogador Não foi Salvo\n" + e.getMessage(), "OK");
                    e.printStackTrace();
                }

            }

            private boolean hasValues() {
                if ((PlayerEditActivity.this.etNome.getText() != null)
                                && (PlayerEditActivity.this.etNome.getText().toString() != null)) {
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_player_edit);
        this.init();
    }


}
