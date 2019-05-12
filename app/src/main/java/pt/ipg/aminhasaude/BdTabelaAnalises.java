package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaAnalises implements BaseColumns {
    public static final String Nome_Tabela = "Analises";
    public static final String Dia = "Dia";
    public static final String Diabetes = "Diabetes";
    public static final String Colestrol = "Colestrol";
    public static final String Creatina = "Creatina";
    public static final String AcidoUrico = "AcidoUrico";
    public static final String Ureia = "Ureia";
    private SQLiteDatabase db;

    public BdTabelaAnalises(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + Nome_Tabela + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Dia + " TEXT NOT NULL, " +
                        Diabetes + " INTERGER NOT NULL, " +
                        Colestrol + " INTERGER NOT NULL, " +
                        Creatina + " INTERGER NOT NULL, " +
                        AcidoUrico + " INTERGER NOT NULL, " +
                        Ureia + " INTERGER NOT NULL " +
                        ")"
        );
    }
        public Cursor query (String[]columns, String selection, String[]selectionArg, String groupby, String having, String orderBy){
            return db.query(Nome_Tabela, columns, selection, selectionArg, groupby, having, orderBy);
        }

        public long insert (ContentValues values){
            return db.insert(Nome_Tabela, null, values);
        }

        public int update (ContentValues values, String whereClause, String []whereArgs){
            return db.update(Nome_Tabela, values, whereClause, whereArgs);
        }
        public int delete (String whereClause, String []whereArgs){
            return db.delete(Nome_Tabela, whereClause, whereArgs);

        }

}
