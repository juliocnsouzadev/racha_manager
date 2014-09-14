package br.com.juliocnsouza.rachamanager.util;

import br.com.juliocnsouza.rachamanager.R;

public class MainMenuImages {

    public static final int JOGADORES = 0;

    public static final int RACHA = 1;


    private final String name;

    private final int type;

    private final int logo;

    public MainMenuImages(final String name, final int type, final int logo) {
        super();
        this.name = name;
        this.type = type;
        this.logo = logo;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }


    public int getImage() {
        switch (this.type) {
            case JOGADORES:
                return R.drawable.soccer_player;
            case RACHA:
                return R.drawable.racha_game;
                default:  return R.drawable.soccer_ball;
        }
        
    }

    public int getLogo() {
        switch (this.logo) {
            case JOGADORES:
                return R.drawable.soccer_player;
            case RACHA:
                return R.drawable.racha_game;
            default:  return R.drawable.soccer_ball;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

}


