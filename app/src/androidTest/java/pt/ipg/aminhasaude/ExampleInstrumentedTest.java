package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Before
    public void apagaBaseDeDados(){
        getAppContext().deleteDatabase(BdOpenHelper.NOME_BASE_DADOS);
    }
    @Test
    public void criaBdSaude() {
        // Context of the app under test.
        Context appContext = getAppContext();

        BdOpenHelper openHelper = new BdOpenHelper(appContext);

        SQLiteDatabase db = openHelper.getReadableDatabase();

        assertTrue(db.isOpen());
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testeCRUD(){
        BdOpenHelper openHelper = new BdOpenHelper(getAppContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();

        BdTabelaTratamento tabelaTratamento = new BdTabelaTratamento(db);
        BdTabelaAnalises tabelaAnalises = new BdTabelaAnalises(db);
        BdTabelaConsulta tabelaConsulta = new BdTabelaConsulta(db);


        //Teste read Tratamento (cRud)
        Cursor cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(0,cursorTratamento.getCount());

        //Teste create/read categorias (CRud)
        String medicamento = "Ben-u-ron";
        String hora = "18:05";
        String HoraATomar = "8";
        int Dias = 5;
        String Doenca = "Gripe";

        long id = criaTratamento(tabelaTratamento,medicamento,hora,HoraATomar,Dias,Doenca);


        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(1,cursorTratamento.getCount());

        Tratamento tratamento = getTratamentoComId(cursorTratamento,id);

        assertEquals(medicamento, tratamento.getMedicamento());
        assertEquals(hora, tratamento.getHora());
        assertEquals(HoraATomar,tratamento.getHoraATomar());
        assertEquals(Dias, tratamento.getdias());
        assertEquals(Doenca, tratamento.getDoenca());

       medicamento = "Bruffn";
       hora = "13:15";
       HoraATomar = "6";
       Dias = 5;
       Doenca = "Dores Musculares";

        id = criaTratamento(tabelaTratamento, medicamento, hora, HoraATomar, Dias,Doenca);

        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(2, cursorTratamento.getCount());

        tratamento = getTratamentoComId(cursorTratamento,id);

        assertEquals(medicamento,tratamento.getMedicamento());
        assertEquals(hora, tratamento.getHora());
        assertEquals(HoraATomar,tratamento.getHoraATomar());
        assertEquals(Dias, tratamento.getdias());
        assertEquals(Doenca, tratamento.getDoenca());

        // Teste Update/Read tratamento (cRUd)
        medicamento = "Bruffen";
        tratamento.setMedicamento(medicamento);

        int registosAlterados = tabelaTratamento.update(tratamento.getContentValues(),BdTabelaTratamento._ID + "=?", new String[]{String.valueOf(id)});

        assertEquals(1,registosAlterados);

        cursorTratamento = getTratamento(tabelaTratamento);
        tratamento = getTratamentoComId(cursorTratamento, id);

        assertEquals(medicamento, tratamento.getMedicamento());

        // Teste Create/Delete/Read Tratamento (CRuD)
        id = criaTratamento(tabelaTratamento,"Plavix", "07:00","24",365,"Coração");
        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(3,cursorTratamento.getCount());

        tabelaTratamento.delete(BdTabelaTratamento._ID + "=?", new String[]{String.valueOf(id)});
        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(2,cursorTratamento.getCount());

        getTratamentoComId(cursorTratamento,id);



        //Teste read Anlises(cRud)

        Cursor cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(0, cursorAnlises.getCount());

        //Teste create/read
        String dia = "2/08/2019";
        double Diabetes = 90;
        double Colestrol = 150;
        double Creatina = 100;
        double AcidoUrico = 100;
        double Ureia = 25;

        id = criaAnalises(tabelaAnalises, dia, Diabetes, Colestrol, Creatina, AcidoUrico, Ureia);
        cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(1,cursorAnlises.getCount());

        Analise analise = getAnliseComID(cursorAnlises,id);
        assertEquals(dia, analise.getDia());
        assertEquals(Diabetes, analise.getdiabetes(), 0.01);
        assertEquals(Colestrol, analise.getColestrol(), 0.01);
        assertEquals(Creatina, analise.getCreatina(), 0.01);
        assertEquals(AcidoUrico, analise.getAcidoUrico(), 0.01);
        assertEquals(Ureia, analise.getUreia(), 0.01);

        //Teste read/update analises (cRUd)

        dia = "02/05/2018";
        analise.setDia(dia);

        tabelaAnalises.update(analise.getContentValues(),BdTabelaAnalises._ID + "=?", new String[]{String.valueOf(id)});

        cursorAnlises = getAnalises(tabelaAnalises);

        analise = getAnliseComID(cursorAnlises,id);
        assertEquals(dia, analise.getDia());

        // Teste read/ delete Analises (cRuD)
        tabelaAnalises.delete(BdTabelaAnalises._ID + "=?", new String[]{String.valueOf(id)});
        cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(0, cursorAnlises.getCount());

        //Teste read consulta (cRud)
        Cursor cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(0, cursorConsulta.getCount());
        // Teste create/read Consulta (CRud)
        String Dia = "20/05/2019";
        String Hora = "14:00";
        String local = "Guarda";
        String motivo = "Consulta de Rotina";
        String medico = "João";

        id = criaConsulta(tabelaConsulta, Dia, Hora, local, motivo, medico);
        cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(1, cursorConsulta.getCount());

        Consulta consulta = getConsultaComID(cursorConsulta, id);
        assertEquals(Dia, consulta.getdia());
        assertEquals(Hora, consulta.getHora());
        assertEquals(local, consulta.getlocal());
        assertEquals(motivo, consulta.getmotivo());
        assertEquals(medico, consulta.getMedico());

        // Teste read/update (cRUd)
        Dia = "21/05/2019";
        local = "Castelo Branco";

        consulta.setdia(Dia);
        consulta.setlocal(local);

        tabelaConsulta.update(consulta.getContentValues(), BdTabelaConsulta._ID + "=?", new String[]{String.valueOf(id)});

        cursorConsulta = getConsulta(tabelaConsulta);

        consulta = getConsultaComID(cursorConsulta, id);
        assertEquals(Dia, consulta.getdia());
        assertEquals(local, consulta.getlocal());

        // Teste read/delete (cRuD)

        tabelaConsulta.delete(BdTabelaConsulta._ID + "=?", new String[]{String.valueOf(id)});
        cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(0, cursorConsulta.getCount());

    }
  
    private long criaTratamento(BdTabelaTratamento tabelaTratamento, String medicamento, String hora, String HoraATomar, int Dias, String Doenca){
       Tratamento tratamento = new Tratamento();
       tratamento.setMedicamento(medicamento);
       tratamento.setHora(hora);
       tratamento.setHoraATomar(HoraATomar);
       tratamento.setdias(Dias);
       tratamento.setDoenca(Doenca);

       long id = tabelaTratamento.insert(tratamento.getContentValues());
       assertNotEquals(-1, id);
       return id;
    }

    private Cursor getTratamento(BdTabelaTratamento tabelaTratamento){
        return tabelaTratamento.query(BdTabelaTratamento.TODAS_COLUNAS,null,null,null
        ,null,null );
    }

    private Tratamento getTratamentoComId(Cursor cursor, long id){
        Tratamento tratamento = null;

        while (cursor.moveToNext()){
            tratamento = Tratamento.fromCursor(cursor);

            if(tratamento.getId() == id){
                break;
            }
        }
        assertNotNull(tratamento);

        return tratamento;
    }
    private long criaAnalises(BdTabelaAnalises tabelaAnalises, String Dia, double Diabetes, double Colestrol, double Creatina, double AcidoUrico, double Ureia){
        Analise analises = new Analise();

        analises.setDia(Dia);
        analises.setdiabetes(Diabetes);
        analises.setColestrol(Colestrol);
        analises.setCreatina(Creatina);
        analises.setAcidoUrico(AcidoUrico);
        analises.setUreia(Ureia);

        long id = tabelaAnalises.insert(analises.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getAnalises(BdTabelaAnalises tabelaAnalises){
        return tabelaAnalises.query(BdTabelaAnalises.TODAS_COLUNAS,null,null,null,null,null);
    }

    private Analise getAnliseComID(Cursor cursor, long id) {
        Analise analise = null;

        while (cursor.moveToNext()) {
            analise = Analise.fromCursor(cursor);

            if (analise.getId() == id) {
                break;
            }
        }

        assertNotNull(analise);

        return analise;
    }
    private long criaConsulta(BdTabelaConsulta tabelaConsulta, String Dia, String Hora, String Local, String Motivo, String Medico ){
        Consulta consulta = new Consulta();
        consulta.setdia(Dia);
        consulta.setHora(Hora);
        consulta.setlocal(Local);
        consulta.setmotivo(Motivo);
        consulta.setMedico(Medico);

        long id = tabelaConsulta.insert(consulta.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getConsulta(BdTabelaConsulta tabelaConsulta){
        return tabelaConsulta.query(BdTabelaConsulta.TODAS_COLUNAS, null, null,null,null,null);
    }

    private Consulta getConsultaComID(Cursor cursor, long id){
        Consulta consulta = null;

        while (cursor.moveToNext()){
            consulta = Consulta.fromCursor(cursor);

            if(consulta.getId() == id ){
                break;
            }
        }

        assertNotNull(consulta);

        return consulta;
    }

}
