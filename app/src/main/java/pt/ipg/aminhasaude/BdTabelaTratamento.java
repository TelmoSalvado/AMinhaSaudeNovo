package pt.ipg.aminhasaude;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTratamento implements BaseColumns {
    public static final String Nome_Tabela = "Tratamento";
    public static final String Nome_Medicamento = "Medicamento";
    public static final String Nome_Doenca = "Doenca";
    public static final String HoraDeComeco = "Hora de Comeco";
    public static final String HoraATomar = "Hora a Tomar";
    public static final String Dias = "Dias";
    private SQLiteDatabase db;

    public BdTabelaTratamento(SQLiteDatabase db){
        this.db = db;
    }
    public void cria(){
        db.execSQL(
                "CREATE TABLE " + Nome_Tabela + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Nome_Medicamento + " TEXT NOT NULL," +
                        Nome_Doenca + "TEXT NOT NULL," +
                        HoraDeComeco + "TEXT NOT NULL," +
                        HoraATomar + " TEXT NOT NULL," +
                        Dias + "INTERGER NOT NULL," +
                        ")"
        );


}
