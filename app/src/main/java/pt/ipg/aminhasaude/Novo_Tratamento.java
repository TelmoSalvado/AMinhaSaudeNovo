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

public class Novo_Tratamento extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int ID_CURSO_LOADER_TRTAMENTOS = 0;

    private EditText textInputLayoutDoenca;
    private EditText textInputLayoutMedicamento;
    private EditText textInputLayoutHora;
    private EditText textInputLayoutHoraATomar;
    private EditText textInputLayoutDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo__tratamento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_TRTAMENTOS, null, this);
        textInputLayoutDoenca = (EditText) findViewById(R.id.EditTextDoenca);
        textInputLayoutMedicamento = (EditText) findViewById(R.id.EditTextMedicamento);
        textInputLayoutHora = (EditText) findViewById(R.id.editTextHoraComeco);
        textInputLayoutHoraATomar = (EditText) findViewById(R.id.EditTextHorasAtomar);
        textInputLayoutDias = (EditText) findViewById(R.id.EditTextDias);
    }


    public void Guardar(View view){

        String medicamento = textInputLayoutMedicamento.getText().toString();

        String hora = textInputLayoutHora.getText().toString();

        String horaatomar = textInputLayoutHoraATomar.getText().toString();
        int dia;

        String dias = textInputLayoutDias.getText().toString();

        String doenca = textInputLayoutDoenca.getText().toString();

        if (doenca.trim().length() == 0){
            textInputLayoutDoenca.setError(getString(R.string.message_required));
            textInputLayoutDoenca.requestFocus();
            return;
        }

        if (medicamento.trim().length() == 0) {
            textInputLayoutMedicamento.setError(getString(R.string.message_required));
            textInputLayoutMedicamento.requestFocus();
            return;
        }
        if (hora.length() != 5 || hora.charAt(2) != ':' ) {
            textInputLayoutHora.setError(getString(R.string.validar_hora));
            textInputLayoutHora.requestFocus();
            return;
        }
        if (horaatomar.length() != 5 || horaatomar.charAt(2) != ':' ) {
            textInputLayoutHoraATomar.setError(getString(R.string.validar_hora));
            textInputLayoutHoraATomar.requestFocus();
            return;
        }
        if ((dias.trim().length() == 0) )  {
            textInputLayoutDias.setError(getString(R.string.message_required));
            textInputLayoutDias.requestFocus();
            return;
        }

        try{
            dia = Integer.parseInt(dias);
        }catch (NumberFormatException e){
            textInputLayoutDias.setError("Dia Invalido!");
            return;
        }
        Tratamento tratamento = new Tratamento();

        tratamento.setMedicamento(medicamento);
        tratamento.setHora(hora);
        tratamento.setHoraATomar(horaatomar);
        tratamento.setdias(dia);
        tratamento.setDoenca(doenca);

        try {
            getContentResolver().insert(AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS,tratamento.getContentValues());

            Toast.makeText(this, getString(R.string.Guardar), Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    textInputLayoutMedicamento,
                    getString(R.string.erro_guardar_tratamento),
                    Snackbar.LENGTH_LONG)
                    .show();

            e.printStackTrace();
        }
    }
    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_TRTAMENTOS, null, this);

        super.onResume();
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS, BdTabelaTratamento.TODAS_COLUNAS, null, null, BdTabelaTratamento.Nome_Medicamento
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



