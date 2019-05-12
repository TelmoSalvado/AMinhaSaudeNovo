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

    public BdTabelaConsulta(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + Nome_Tabela + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Dia + " TEXT NOT NULL, " +
                        Hora + " INTERGER NOT NULL, " +
                        Local + " INTERGER NOT NULL, " +
                        Motivo + " INTERGER NOT NULL, " +
                        Medico + " INTERGER NOT NULL " +
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
