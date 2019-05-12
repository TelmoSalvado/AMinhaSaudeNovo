package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;

public class Tratamento{



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMedicamento(){
        return Medicamento;
    }
    public void setMedicamento(String Medicamento){
        this.Medicamento = Medicamento;

    }
    private String Medicamento;

    public String getHora(){

        return Hora;
    }
    public void setHora(String Hora){
        this.Hora = Hora;
    }
    private String Hora;

    public String getHoraATomar(){
        return HoraATomar;
    }
    public void setHoraATomar(String HoraATomar){
        this.HoraATomar = HoraATomar;

    }
    private String HoraATomar;

    public int getdias(){
        return dias;
    }
    public void setdias(int dias){
        this.dias = dias;
    }
    private int dias;

    public String getDoenca(){
        return Doenca;
    }
    public void setDoenca(String Doenca){
        this.Doenca = Doenca;

    }
    private String Doenca;
    private long id;

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTabelaTratamento.Nome_Medicamento, Medicamento);
        valores.put(BdTabelaTratamento.HoraDeComeco,Hora);
        valores.put(BdTabelaTratamento.HoraATomar,HoraATomar);
        valores.put(BdTabelaTratamento.Dias,dias);
        valores.put(BdTabelaTratamento.Nome_Doenca,Doenca);
        return valores;
    }

    public static Tratamento fromCursor (Cursor cursor) {

        Tratamento tratamento = new Tratamento();

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTabelaTratamento._ID)
        );

        String Medicamento = cursor.getString(
                cursor.getColumnIndex(BdTabelaTratamento.Nome_Medicamento)
        );
        String Hora = cursor.getString(cursor.getColumnIndex(BdTabelaTratamento.HoraDeComeco));
        String HoraATomar = cursor.getString(cursor.getColumnIndex(BdTabelaTratamento.HoraATomar));
        Integer dias = cursor.getInt(cursor.getColumnIndex(BdTabelaTratamento.Dias));
        String Doenca = cursor.getString(cursor.getColumnIndex(BdTabelaTratamento.Nome_Doenca));

        tratamento.setId(id);
        tratamento.setMedicamento(Medicamento);
        tratamento.setHora(Hora);
        tratamento.setHoraATomar(HoraATomar);
        tratamento.setdias(dias);
        tratamento.setDoenca(Doenca);
        return tratamento;
    }

}
