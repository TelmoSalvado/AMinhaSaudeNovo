package pt.ipg.aminhasaude;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

public class EditarTratamento extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ID_CURSOR_LOADER_TRATAMENTOS = 0;

    private EditText textInputLayoutDoenca;
    private EditText textInputLayoutMedicamento;
    private EditText textInputLayoutHora;
    private EditText textInputLayoutHoraATomar;
    private EditText textInputLayoutDias;

    private Tratamento tratamento = null;

    private Uri enderecoTratamentoEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tratamento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textInputLayoutDoenca = (EditText) findViewById(R.id.EditTextDoenca);
        textInputLayoutMedicamento = (EditText) findViewById(R.id.EditTextMedicamento);
        textInputLayoutHora = (EditText) findViewById(R.id.editTextHoraComeco);
        textInputLayoutHoraATomar = (EditText) findViewById(R.id.EditTextHorasAtomar);
        textInputLayoutDias = (EditText) findViewById(R.id.EditTextDias);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_TRATAMENTOS, null, this);

        Intent intent = getIntent();

        long idTratamento = intent.getLongExtra(VerTratamentos.ID_TRATAMENTO,-1);

        if(idTratamento == -1){
            Toast.makeText(this, "Erro: não foi possivel ler o tratamento!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoTratamentoEditar = Uri.withAppendedPath(AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS, String.valueOf(idTratamento));

        Cursor cursor = getContentResolver().query(enderecoTratamentoEditar, BdTabelaTratamento.TODAS_COLUNAS, null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel ler o Tratamento!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        tratamento = Tratamento.fromCursor(cursor);

        textInputLayoutDoenca.setText(tratamento.getMedicamento());
        textInputLayoutMedicamento.setText(tratamento.getMedicamento());
        textInputLayoutHora.setText(tratamento.getHora());
        textInputLayoutHoraATomar.setText(tratamento.getHoraATomar());
        textInputLayoutDias.setText(String.valueOf(tratamento.getdias()));

    }
    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_TRATAMENTOS, null, this);

        super.onResume();
    }
    public void Guardar(View view){

        String medicamento = textInputLayoutMedicamento.getText().toString();


        String hora = textInputLayoutHora.getText().toString();


        String horaatomar = textInputLayoutHoraATomar.getText().toString();

        String dias = textInputLayoutDias.getText().toString();
        int dia;

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
            getContentResolver().update(enderecoTratamentoEditar, tratamento.getContentValues(), null, null);

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

        Toast.makeText(this, "Guardado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void Cancelar(View view){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        finish();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS, BdTabelaTratamento.TODAS_COLUNAS, null, null, BdTabelaTratamento.Nome_Medicamento);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
