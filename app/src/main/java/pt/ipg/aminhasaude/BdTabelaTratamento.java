package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTratamento implements BaseColumns {
    public static final String Nome_Tabela = "Tratamento";
    public static final String Nome_Medicamento = "Medicamento";
    public static final String Nome_Doenca = "Doenca";
    public static final String HoraDeComeco = "HoraDeComeco";
    public static final String HoraATomar = "HoraATomar";
    public static final String Dias = "Dias";
    private SQLiteDatabase db;

    public BdTabelaTratamento(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + Nome_Tabela + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Nome_Medicamento + " TEXT NOT NULL, " +
                        Nome_Doenca + " TEXT NOT NULL, " +
                        HoraDeComeco + " TEXT NOT NULL, " +
                        HoraATomar + " TEXT NOT NULL, " +
                        Dias + " INTERGER NOT NULL " +
                        ")"
        );


    }
    public Cursor query(String[] columns, String selection, String[] selectionArg, String groupby, String having, String orderBy) {
        return  db.query(Nome_Tabela, columns, selection, selectionArg, groupby, having, orderBy);
    }

    public long insert(ContentValues values){
        return db.insert(Nome_Tabela, null, values);
    }

    public int update(ContentValues values, String whereClause, String [] whereArgs){
        return db.update(Nome_Tabela, values, whereClause, whereArgs);
    }
    public int delete(String whereClause, String [] whereArgs){
        return db.delete(Nome_Tabela, whereClause, whereArgs);

    }
}
