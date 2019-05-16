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
        assertNotEquals(0,cursorTratamento.getCount());

        //Teste create/read categorias (CRud)
        String nome = "Ben-u-ron";

        long idBenURon = criaTratamento(tabelaTratamento,nome);

        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(1,cursorTratamento.getCount());

        Tratamento tratamento = getTratamentoComId(cursorTratamento,idBenURon);

        assertEquals(nome, tratamento.getMedicamento());

        nome = "Bruffn";
        long idBruffen = criaTratamento(tabelaTratamento, nome);

        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(2, cursorTratamento.getCount());

        tratamento = getTratamentoComId(cursorTratamento,idBruffen);

        assertEquals(nome,tratamento.getMedicamento());

        // Teste Update/Read tratamento (cRUd)
        nome = "Bruffen";
        tratamento.setMedicamento(nome);

        int registosAlterados = tabelaTratamento.update(tratamento.getContentValues(),BdTabelaTratamento._ID + "=?", new String[]{String.valueOf(idBruffen)});

        assertEquals(1,registosAlterados);

        cursorTratamento = getTratamento(tabelaTratamento);
        tratamento = getTratamentoComId(cursorTratamento, idBruffen);

        assertEquals(nome, tratamento.getMedicamento());

        // Teste Create/Delete/Read Tratamento (CRuD)
        long id = criaTratamento(tabelaTratamento,"TESTE");
        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(3,cursorTratamento.getCount());

        tabelaTratamento.delete(BdTabelaTratamento._ID + "=?", new String[]{String.valueOf(id)});
        cursorTratamento = getTratamento(tabelaTratamento);
        assertEquals(2,cursorTratamento.getCount());

        getTratamentoComId(cursorTratamento,idBenURon);
        getTratamentoComId(cursorTratamento, idBruffen);


        //Teste read Anlises(cRud)

        Cursor cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(0, cursorAnlises.getCount());

        //Teste create/read
        String dia = "2/08/2019";
        int Diabetess = 90;
        int Colestrol = 150;
        int Creatina = 100;
        int AcidoUrico = 100;
        int Ureia = 25;

        id = criaAnalises(tabelaAnalises,dia, Diabetess, Colestrol, Creatina, AcidoUrico, Ureia);
        cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(1,cursorAnlises.getCount());

        Analise analise = getAnlisesComID(cursorAnlises,id);
        assertEquals(dia, analise.getDia());
        assertEquals(Diabetess, analise.getdiabetes());
        assertEquals(Colestrol, analise.getColestrol());
        assertEquals(Creatina, analise.getCreatina());
        assertEquals(AcidoUrico, analise.getAcidoUrico());
        assertEquals(Ureia, analise.getUreia());

        //Teste read/update analises (cRUd)
        analise = getAnlisesComID(cursorAnlises, id);
        dia = "02/05/2018";

        analise.setDia(dia);

        tabelaAnalises.update(analise.getContentValues(),BdTabelaAnalises._ID + "=?", new String[]{String.valueOf(id)});

        cursorAnlises = getAnalises(tabelaAnalises);

        analise = getAnlisesComID(cursorAnlises,id);
        assertEquals(dia, analise.getDia());

        // Teste read/ delete Analises (cRuD)
        tabelaAnalises.delete(BdTabelaAnalises._ID + "=?", new String[]{String.valueOf(id)});
        cursorAnlises = getAnalises(tabelaAnalises);
        assertEquals(1, cursorAnlises.getCount());

        //Teste read consulta (cRud)
        Cursor cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(0, cursorConsulta.getCount());
        // Teste create/read Consulta (CRud)
        String Dia = "20/05/2019";
        String hora = "14:00";
        String local = "Guarda";
        String motivo = "Consulta de Rotina";
        String medico = "João";

        id = criaConsulta(tabelaConsulta, Dia, hora, local, motivo, medico);
        cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(1,cursorConsulta);

        Consulta consulta = getConsultaComID(cursorConsulta, id);
        assertEquals(Dia, consulta.getdia());
        assertEquals(hora,consulta.getHora());
        assertEquals(local, consulta.getlocal());
        assertEquals(motivo, consulta.getmotivo());
        assertEquals(medico, consulta.getMedico());

        // Teste read/update (cRUd)
        consulta = getConsultaComID(cursorConsulta, id);
        dia = "21/05/2019";
        local = "Castelo Branco";

        tabelaConsulta.update(consulta.getContentValues(), BdTabelaConsulta._ID + "=?", new String[]{String.valueOf(id)});

        cursorConsulta = getConsulta(tabelaConsulta);

        consulta = getConsultaComID(cursorConsulta, id);
        assertEquals(dia, consulta.getdia());
        assertEquals(local, consulta.getlocal());

        // Teste read/delete (cRuD)

        tabelaConsulta.delete(BdTabelaConsulta._ID + "=?", new String[]{String.valueOf(id)});
        cursorConsulta = getConsulta(tabelaConsulta);
        assertEquals(1, cursorConsulta.getCount());

    }
  
    private long criaTratamento(BdTabelaTratamento tabelaTratamento, String nome){
       Tratamento tratamento = new Tratamento();
       tratamento.setMedicamento(nome);

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
    private long criaAnalises(BdTabelaAnalises tabelaAnalises, String Dia, int Diabetes, int Colestrol, int Creatina, int AcidoUrico, int Ureia){
        Analise analises = new Analise();

        analises.setDia(Dia);
        analises.setdiabetes(Diabetes);
        analises.setColestrol(Colestrol);
        analises.setCreatina(Creatina);
        analises.setAcidoUrico(AcidoUrico);
        analises.setUreia(Ureia);

        long id = tabelaAnalises.insert(analises.getContentValues());
        assertEquals(-1, id);

        return id;
    }

    private Cursor getAnalises(BdTabelaAnalises tabelaAnalises){
        return tabelaAnalises.query(BdTabelaAnalises.TODAS_COLUNAS,null,null,null,null,null);
    }

    private Analise getAnlisesComID(Cursor cursor, long id) {
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
