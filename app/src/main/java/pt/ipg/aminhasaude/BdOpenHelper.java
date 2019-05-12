package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BASE_DADOS = "AMinhaSaude.db";
    private static final int VERSAO_BASE_DADOS = 1;

    public BdOpenHelper(@Nullable Context context){
        super(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS);
       }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new BdTabelaTratamento(db).cria();
        new BdTabelaAnalises(db).cria();
        new BdTabelaConsulta(db).cria();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
