package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;

public class Analise {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getDia(){

        return Dia;
    }
    public void setDia(String Dia){
        this.Dia = Dia;

    }
    private String Dia;

    public int getdiabetes(){
        return diabetes;
    }
    public void setdiabetes(int diabetes){
        this.diabetes = diabetes;
    }
    private int diabetes;

    public int getColestrol(){
        return Colestrol;
    }
    public void setColestrol(int Colestrol){
        this.Colestrol = Colestrol;
    }
    private int Colestrol;

    public int getCreatina(){
        return Creatina;
    }
    public void setCreatina(int Creatina){
        this.Creatina = Creatina;
    }
    private int Creatina;

    public int getAcidoUrico(){
        return AcidoUrico;
    }
    public void setAcidoUrico(int AcidoUrico){
        this.AcidoUrico = AcidoUrico;
    }
    private int AcidoUrico;

    public int getUreia(){
        return Ureia;
    }
    public void setUreia(int Ureia){
        this.Ureia = Ureia;
    }
    private int Ureia;
    private long id;

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTabelaAnalises.Dia,Dia);
        valores.put(BdTabelaAnalises.Diabetes,diabetes);
        valores.put(BdTabelaAnalises.Colestrol,Colestrol);
        valores.put(BdTabelaAnalises.Creatina,Creatina);
        valores.put(BdTabelaAnalises.AcidoUrico,AcidoUrico);
        valores.put(BdTabelaAnalises.Ureia,Ureia);
        return valores;
    }

    public static Analise fromCursor (Cursor cursor) {

        Analise analise = new Analise();

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTabelaAnalises._ID)
        );

        String Dia = cursor.getString(
                cursor.getColumnIndex(BdTabelaAnalises.Dia)
        );
        Integer Diabetes = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Diabetes));
        Integer Colestrol = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Colestrol));
        Integer Creatina = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Creatina));
        Integer AcidoUrico = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.AcidoUrico));
        Integer Ureia = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Ureia));

        analise.setId(id);
        analise.setDia(Dia);
        analise.setdiabetes(Diabetes);
        analise.setColestrol(Colestrol);
        analise.setCreatina(Creatina);
        analise.setAcidoUrico(AcidoUrico);
        analise.setUreia(Ureia);
        return analise;
    }

}
