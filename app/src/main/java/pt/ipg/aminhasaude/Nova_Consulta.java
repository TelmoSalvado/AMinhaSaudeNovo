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

        String local = textInputLayoutLocal.getText().toString();


        String motivo = textInputLayoutMotivo.getText().toString();


        String Medico = textInputLayoutMedico.getText().toString();

        if (dia.length() != 10 || dia.charAt(2) != '/' || dia.charAt(5) != '/') {
            textInputLayoutDia.setError(getString(R.string.Validar_data));
            textInputLayoutDia.requestFocus();
            return;
        }

        if (hora.length() != 5 || hora.charAt(2) != ':' ) {
            textInputLayoutHora.setError(getString(R.string.validar_hora));
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
