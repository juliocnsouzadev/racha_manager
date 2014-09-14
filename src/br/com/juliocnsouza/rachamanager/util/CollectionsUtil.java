package br.com.juliocnsouza.rachamanager.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.juliocnsouza.rachamanager.entity.Player;

public class CollectionsUtil {
    public static String[] getArray(final Collection<Player> collection) {

        final String[] strs = new String[collection.size()];
        int i = 0;
        for (final Player o : collection) {
            strs[i] = o.getNome();
            i++;
        }
        return strs;
    }

    public static String[] getArrayStr(final Collection<String> collection) {
        final Object[] objs = collection.toArray();
        final String[] strs = new String[objs.length];
        int i = 0;
        for (final Object o : objs) {
            strs[i] = String.valueOf(o);
            i++;
        }
        return strs;
    }

    public static List<String> getList(final String[] collection) {

        final List<String> toReturn = new ArrayList<String>();
        for (final String o : collection) {
            toReturn.add(o);
        }
        return toReturn;
    }
}
