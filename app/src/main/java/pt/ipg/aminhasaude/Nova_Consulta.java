package pt.ipg.aminhasaude;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.android.material.snackbar.Snackbar;

public class Nova_Consulta extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int ID_CURSO_LOADER_CONSULTA = 0;

    private EditText textInputLayoutDia;
    private EditText textInputLayoutHora;
    private EditText textInputLayoutLocal;
    private EditText textInputLayoutMotivo;
    private EditText textInputLayoutMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova__consulta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_CONSULTA, null, this);
        textInputLayoutDia = (EditText) findViewById(R.id.EditTextDia);
        textInputLayoutHora = (EditText) findViewById(R.id.EditTextHora);
        textInputLayoutLocal = (EditText) findViewById(R.id.EditTextLocal);
        textInputLayoutMotivo = (EditText) findViewById(R.id.EditTextMotivo);
        textInputLayoutMedico = (EditText) findViewById(R.id.EditTextMedico);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Guardar(View view){

        String dia = textInputLayoutDia.getText().toString();


        String hora = textInputLayoutHora.getText().toString();

        String horas[] = hora.split(":");

        int Horas = Integer.parseInt(horas[0]);
        int Minutos = Integer.parseInt(horas[1]);

        String local = textInputLayoutLocal.getText().toString();


        String motivo = textInputLayoutMotivo.getText().toString();

        String Medico = textInputLayoutMedico.getText().toString();

        String[] dataSeparada = dia.split("/");

        int Dia = Integer.parseInt(dataSeparada[0]);
        int Mes = Integer.parseInt(dataSeparada[1]);
        int Ano = Integer.parseInt(dataSeparada[2]);

        if (dia.length() != 10 || dia.charAt(2) != '/' || dia.charAt(5) != '/') {
            textInputLayoutDia.setError(getString(R.string.Validar_data));
            textInputLayoutDia.requestFocus();
            return;
        }else if(Dia > 30 && (Mes == 6 || Mes == 4 || Mes == 9 || Mes == 11)  ) {
            textInputLayoutDia.setError("Error na Data");
            textInputLayoutDia.requestFocus();
            return;
        }else if (Dia > 29 && Mes == 2){
            textInputLayoutDia.setError("Erro na data");
            textInputLayoutDia.requestFocus();
            return;
        }else if(Dia <= 0 || Dia > 31 || Mes <= 0 || Mes > 12 || Ano < 2019){
            textInputLayoutDia.setError("Erro na data");
            textInputLayoutDia.requestFocus();
            return;
        }else if (dia.trim().length()==0){
            textInputLayoutDia.setError("Erroo");
            textInputLayoutDia.requestFocus();
            return;
        }

        if (hora.length() != 5 || hora.charAt(2) != ':' ) {
            textInputLayoutHora.setError(getString(R.string.validar_hora));
            textInputLayoutHora.requestFocus();
            return;
        }else if( Horas < 0 || Horas >24 || Minutos <0 || Minutos > 60){
            textInputLayoutHora.setError("Hora Inválida");
            textInputLayoutHora.requestFocus();
            return;
        }
        if (local.trim().length()  == 0) {
            textInputLayoutLocal.setError(getString(R.string.message_required));
            textInputLayoutLocal.requestFocus();
            return;
        }
        if (motivo.trim().length() == 0) {
            textInputLayoutMotivo.setError(getString(R.string.message_required));
            textInputLayoutMotivo.requestFocus();
            return;
        }
        if (Medico.trim().length() == 0) {
            textInputLayoutMedico.setError(getString(R.string.message_required));
            textInputLayoutMedico.requestFocus();
            return;
        }

        Consulta consulta = new Consulta();

        consulta.setdia(dia);
        consulta.setHora(hora);
        consulta.setlocal(local);
        consulta.setmotivo(motivo);
        consulta.setMedico(Medico);

        try {
            getContentResolver().insert(AMinhaSaudeContentProvider.ENDERECO_CONSULTA,consulta.getContentValues());

            Toast.makeText(this, getString(R.string.Guardar), Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    textInputLayoutDia,
                    getString(R.string.erro_guardar_tratamento),
                    Snackbar.LENGTH_LONG)
                    .show();

            e.printStackTrace();
        }

    }
    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_CONSULTA, null, this);

        super.onResume();
    }

    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_CONSULTA, BdTabelaConsulta.TODAS_COLUNAS, null, null, BdTabelaConsulta.Dia
        );

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
