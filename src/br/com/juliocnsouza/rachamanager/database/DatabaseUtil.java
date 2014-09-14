package br.com.juliocnsouza.rachamanager.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.juliocnsouza.rachamanager.entity.Player;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseUtil extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "hcm.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseUtil(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ConnectionSource getConnectionSource(final Context context) {
        return new DatabaseUtil(context).getConnectionSource();
    }

    @Override
    public void onCreate(final SQLiteDatabase db, final ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Player.class);
            
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final ConnectionSource connectionSource,
                    final int oldDbVersion, final int newDbVersion) {
        try {
            TableUtils.dropTable(connectionSource, Player.class, true);
            onCreate(db, connectionSource);

        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

}
