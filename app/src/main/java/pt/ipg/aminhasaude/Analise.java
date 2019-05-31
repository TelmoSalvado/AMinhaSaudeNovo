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

    public double getdiabetes(){
        return diabetes;
    }
    public void setdiabetes(double diabetes){
        this.diabetes = diabetes;
    }
    private double diabetes;

    public double getColestrol(){
        return Colestrol;
    }
    public void setColestrol(double Colestrol){
        this.Colestrol = Colestrol;
    }
    private double Colestrol;

    public double getCreatina(){
        return Creatina;
    }
    public void setCreatina(double Creatina){
        this.Creatina = Creatina;
    }
    private double Creatina;

    public double getAcidoUrico(){
        return AcidoUrico;
    }
    public void setAcidoUrico(double AcidoUrico){
        this.AcidoUrico = AcidoUrico;
    }
    private double AcidoUrico;

    public double getUreia(){
        return Ureia;
    }
    public void setUreia(double Ureia){
        this.Ureia = Ureia;
    }
    private double Ureia;
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
        double Diabetes = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Diabetes));
        double Colestrol = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Colestrol));
        double Creatina = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Creatina));
        double AcidoUrico = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.AcidoUrico));
        double Ureia = cursor.getInt(cursor.getColumnIndex(BdTabelaAnalises.Ureia));

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
