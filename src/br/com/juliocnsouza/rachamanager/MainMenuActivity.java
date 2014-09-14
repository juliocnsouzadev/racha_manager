package br.com.juliocnsouza.rachamanager;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.com.juliocnsouza.rachamanager.adapters.MainMenuImagesAdapter;
import br.com.juliocnsouza.rachamanager.util.MainMenuImages;

public class MainMenuActivity extends ListActivity {

    /**
     * metodo que cria a lista de opções no menu
     */
    private void createListMenu() {
        final ArrayList<MainMenuImages> imgsList = new ArrayList<MainMenuImages>();
        imgsList.add(new MainMenuImages("Lista Jogadores", MainMenuImages.JOGADORES,
                        MainMenuImages.JOGADORES));
        imgsList.add(new MainMenuImages("Começar Racha", MainMenuImages.RACHA, MainMenuImages.RACHA));
        this.setListAdapter(new MainMenuImagesAdapter(this, imgsList));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_menu);
        this.createListMenu();
    }

    @Override
    protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
        super.onListItemClick(l, v, position, id);
        final MainMenuImages icon = (MainMenuImages) this.getListAdapter().getItem(position);
        Toast.makeText(this, "Você selecionou " + icon.getName(), Toast.LENGTH_SHORT).show();

        // navigate acording to menu item
        switch (position) {
            case 0:
                final Intent gotoPlayers = new Intent(MainMenuActivity.this, PlayersActivity.class);
                this.startActivity(gotoPlayers);
                this.overridePendingTransition(R.anim.push_side_in, R.anim.push_side_out);
                this.finish();
                break;

            case 1:
                final Intent gotoRacha = new Intent(MainMenuActivity.this, RachaActivity.class);
                this.startActivity(gotoRacha);
                this.overridePendingTransition(R.anim.push_side_in, R.anim.push_side_out);
                this.finish();
                break;

            default:
                // finish();
        }

    }


}
