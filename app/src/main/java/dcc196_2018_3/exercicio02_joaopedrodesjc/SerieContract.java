package dcc196_2018_3.exercicio02_joaopedrodesjc;

import android.provider.BaseColumns;

public class SerieContract {
    public final class Serie implements BaseColumns {
        public final static String TABLE_NAME = "Serie";
        public final static String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_TEMP = "temporada";
        public static final String COLUMN_NAME_EP = "episodio";
        public final static String CREATE_SERIE  = "CREATE TABLE "+Serie.TABLE_NAME+" ("
                + Serie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Serie.COLUMN_NAME_TITULO+ " TEXT, "
                + Serie.COLUMN_NAME_TEMP+ " INTEGER,"
                + Serie.COLUMN_NAME_EP+ " INTEGER"
                +")";
        public final static String DROP_SERIE = "DROP TABLE IF EXISTS "+Serie.TABLE_NAME;
    }
}