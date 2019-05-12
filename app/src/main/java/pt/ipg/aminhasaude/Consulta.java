package pt.ipg.aminhasaude;

import android.content.ContentValues;
import android.database.Cursor;

public class Consulta {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getdia(){
        return dia;
    }
    public void setdia(String dia){
        this.dia = dia;

    }
    private String dia;

    public String getHora(){

        return Hora;
    }
    public void setHora(String Hora){
        this.Hora = Hora;
    }
    private String Hora;

    public String getlocal(){

        return local;
    }
    public void setlocal(String local){
        this.local = local;
    }
    private String local;

    public String getmotivo(){

        return motivo;
    }
    public void setmotivo(String motivo){
        this.motivo = motivo;
    }
    private String motivo;

    public String getMedico(){

        return Medico;
    }
    public void setMedico(String Medico){
        this.Medico = Medico;
    }
    private String Medico;

    private long id;

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTabelaConsulta.Dia,dia);
        valores.put(BdTabelaConsulta.Hora,Hora);
        valores.put(BdTabelaConsulta.Local,local);
        valores.put(BdTabelaConsulta.Motivo,motivo);
        valores.put(BdTabelaConsulta.Medico,Medico);
        return valores;
    }

    public static Consulta fromCursor (Cursor cursor) {

        Consulta consulta = new Consulta();

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTabelaConsulta._ID)
        );

        String Dia = cursor.getString(
                cursor.getColumnIndex(BdTabelaConsulta.Dia)
        );
        String Hora = cursor.getString(cursor.getColumnIndex(BdTabelaConsulta.Hora));
        String Local = cursor.getString(cursor.getColumnIndex(BdTabelaConsulta.Local));
        String Motivo = cursor.getString(cursor.getColumnIndex(BdTabelaConsulta.Motivo));
        String Medico = cursor.getString(cursor.getColumnIndex(BdTabelaConsulta.Medico));

        consulta.setId(id);
        consulta.setdia(Dia);
        consulta.setdia(Hora);
        consulta.setlocal(Local);
        consulta.setmotivo(Motivo);
        consulta.setMedico(Medico);
        return consulta;
    }

}
