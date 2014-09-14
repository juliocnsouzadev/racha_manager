package br.com.juliocnsouza.rachamanager.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertBuilder {

    public enum DialogColor {
        BLACK, WHITE
    }


    /**
     * Show a simple Dialog just for message, no actions.
     * 
     * @param context
     * @param message
     * @param positive
     */
    public static void showSimpleDialog(final Context context, final String title,
                    final String message, final String positive) {
        final Context contextPassed = context;
        final AlertDialog.Builder dialog = new AlertDialog.Builder(contextPassed);
        dialog.setMessage(message);
        dialog.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface di, final int arg) {

            }
        });
        dialog.setTitle(title.toUpperCase());
        // dialog.show();
        final AlertDialog d = dialog.create();
        mostraDialog(d, true);
    }
    
    public static void mostraDialog(final AlertDialog dialog, final boolean temTitulo) {
        dialog.show();
    }


}
