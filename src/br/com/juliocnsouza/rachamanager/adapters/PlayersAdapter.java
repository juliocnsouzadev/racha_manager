package br.com.juliocnsouza.rachamanager.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.juliocnsouza.rachamanager.R;
import br.com.juliocnsouza.rachamanager.entity.Player;

public class PlayersAdapter extends BaseAdapter {

    private final Context context;
    private final List<Player> lista;

    public PlayersAdapter(final Context context, final List<Player> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(final int posicao) {
        final Player player = this.lista.get(posicao);
        return player;
    }

    @Override
    public long getItemId(final int posicao) {
        return posicao;
    }

    @Override
    public View getView(final int posicao, final View convertView, final ViewGroup parent) {
        final Player player = this.lista.get(posicao);
        final LayoutInflater inflater =
                        (LayoutInflater) this.context
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.activity_players_list_item, null);
        final TextView tvNome = (TextView) v.findViewById(R.id.tvMenuPlayerName);
        final TextView tvGoalKeeper = (TextView) v.findViewById(R.id.tvMenuPlayerGoalKeeperSkills);
        final TextView tvDefense = (TextView) v.findViewById(R.id.tvMenuPlayerDefenseSkills);
        final TextView tvAttack = (TextView) v.findViewById(R.id.tvMenuPlayerAttackSkills);

        if (player != null) {
            tvNome.setText(player.getNome());
            tvGoalKeeper.setText("Habilidades Goleiro\t"
                            + String.valueOf(player.getGoalKeeperSkills()));
            tvDefense.setText("Habilidades Defesa:\t" + String.valueOf(player.getDefenseSkills()));
            tvAttack.setText("Habilidades Ataque:\t" + String.valueOf(player.getAttackSkills()));
        }
        return v;
    }
}
