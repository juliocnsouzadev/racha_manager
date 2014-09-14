package br.com.juliocnsouza.rachamanager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import br.com.juliocnsouza.rachamanager.adapters.PlayersAdapter;
import br.com.juliocnsouza.rachamanager.database.GenericDAO;
import br.com.juliocnsouza.rachamanager.entity.Player;
import br.com.juliocnsouza.rachamanager.util.AlertBuilder;
import br.com.juliocnsouza.rachamanager.util.BundleBuilder;

public class PlayersActivity extends ListActivity {


    /**
     * metodo que cria a lista de opções no menu
     */
    private void createListMenu() {
        List<Player> playersList = null;


        final GenericDAO<Player, String> playersDao =
                        new GenericDAO<Player, String>(this, Player.class);
        try {
            playersList = playersDao.callDAO().queryForAll();
        } catch (final SQLException e) {
            e.printStackTrace();
            AlertBuilder.showSimpleDialog(this, "Erro ao buscar Jogadores", e.getMessage(), "OK");
        }
        if (playersList == null) {
            playersList = new ArrayList<Player>();
            AlertBuilder.showSimpleDialog(this, "Nenhum Jogador Encontrado",
                            "Adicione novos jogadores", "OK");
        }
        Collections.sort(playersList);
        this.setListAdapter(new PlayersAdapter(this, playersList));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_players);
        this.createListMenu();
        this.getListView().setLongClickable(true);
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapter, final View view,
                            final int position, final long arg3) {
                final Player player =
                                (Player) PlayersActivity.this.getListAdapter().getItem(position);
                PlayersActivity.this.showToast(player);
                final Intent intent = new Intent(PlayersActivity.this, PlayerEditActivity.class);
                intent.putExtras(PlayersActivity.this.playerToEdit(player));
                PlayersActivity.this.startActivity(intent);
                PlayersActivity.this.overridePendingTransition(R.anim.push_side_in,
                                R.anim.push_side_out);
                PlayersActivity.this.finish();
                return true;
            }
        });
    }

    private void showToast(final Player player) {
        Toast.makeText(this, "Você selecionou " + player.getNome() + " para edição",
                        Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
        super.onListItemClick(l, v, position, id);
        final Player player = (Player) this.getListAdapter().getItem(position);
        Toast.makeText(this, "Você selecionou " + player.getNome(), Toast.LENGTH_SHORT).show();


    }

    private Bundle playerToEdit(final Player player) {
        return new BundleBuilder().putSerializable(BundleBuilder.Key.SELECTED_PLAYER, player)
                        .create();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.players, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_new) {
            final Intent gotoNewPlayer = new Intent(PlayersActivity.this, PlayerEditActivity.class);
            this.startActivity(gotoNewPlayer);
            this.overridePendingTransition(R.anim.push_side_in, R.anim.push_side_out);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        final Intent gotoMainMenu = new Intent(PlayersActivity.this, MainMenuActivity.class);
        this.startActivity(gotoMainMenu);
        this.overridePendingTransition(R.anim.push_side_in, R.anim.push_side_out);
        this.finish();
    }

}
