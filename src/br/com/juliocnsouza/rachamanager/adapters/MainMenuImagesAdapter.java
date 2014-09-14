package br.com.juliocnsouza.rachamanager.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.juliocnsouza.rachamanager.R;
import br.com.juliocnsouza.rachamanager.util.MainMenuImages;

public class MainMenuImagesAdapter extends BaseAdapter {

    private final Context context;
    private final List<MainMenuImages> lista;

    public MainMenuImagesAdapter(final Context context, final List<MainMenuImages> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(final int posicao) {
        final MainMenuImages icone = this.lista.get(posicao);
        return icone;
    }

    @Override
    public long getItemId(final int posicao) {
        return posicao;
    }

    @Override
    public View getView(final int posicao, final View convertView, final ViewGroup parent) {
        final MainMenuImages icone = this.lista.get(posicao);
        final LayoutInflater inflater =
                        (LayoutInflater) this.context
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.activity_main_menu_list_item, null);
        final TextView textNome = (TextView) v.findViewById(R.id.menuItemName);
        textNome.setText(icone.getName());
        final ImageView img = (ImageView) v.findViewById(R.id.menuImg);
        img.setImageResource(icone.getImage());
        return v;
    }
}
