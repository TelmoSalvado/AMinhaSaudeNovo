package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaConsulta implements BaseColumns {
    public static final String Nome_Tabela = "Consulta";
    public static final String Dia = "Dia";
    public static final String Hora = "Hora";
    public static final String Local = "Local";
    public static final String Motivo = "Motivo";
    public static final String Medico = "Medico";
    private SQLiteDatabase db;

    public static final String[] TODAS_COLUNAS = new String[] { _ID, Dia, Hora, Local, Motivo, Medico };

    public BdTabelaConsulta(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + Nome_Tabela + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Dia + " TEXT NOT NULL, " +
                        Hora + " TEXT NOT NULL, " +
                        Local + " TEXT NOT NULL, " +
                        Motivo + " TEXT NOT NULL, " +
                        Medico + " TEXT NOT NULL " +
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
