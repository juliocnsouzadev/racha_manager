package br.com.juliocnsouza.rachamanager.database;

import java.sql.SQLException;

import android.content.Context;
import br.com.juliocnsouza.rachamanager.util.AlertBuilder;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class GenericDAO<T, ID> {

    private final Context context;
    private ConnectionSource con;
    private final Class<T> clazz;
    private Dao<T, ID> dao;

    public GenericDAO(final Context context, final Class<T> clazz) {
        super();
        this.clazz = clazz;
        this.context = context;
    }

    public Dao<T, ID> callDAO() {
        if (con == null) {
            con = new DatabaseUtil(context).getConnectionSource();
        }
        if (dao != null) {
            return dao;
        }
        try {
            dao = DaoManager.createDao(con, clazz);
            return dao;
        } catch (final SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            AlertBuilder.showSimpleDialog(
                            context,
                            "Erro",
                            "Falha falha de acesso ao banco de dados. Detalhes:\n" + e.getMessage(),
                            "OK");
            return null;
        }
    }

}
