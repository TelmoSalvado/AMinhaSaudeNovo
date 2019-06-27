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

public class NovosResultadosAnalises extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSO_LOADER_ANALISES = 0;

    private EditText textInputLayoutDia;
    private EditText textInputLayoutAcucar;
    private EditText textInputLayoutColestrol;
    private EditText textInputLayoutCreatina;
    private EditText textInputLayoutAcidoUrico;
    private EditText textInputLayoutUreia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novos_resultados_analises);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_ANALISES, null, this);
        textInputLayoutDia = (EditText) findViewById(R.id.editTextDataAnalises);
        textInputLayoutAcucar = (EditText) findViewById(R.id.editTextAcucar);
        textInputLayoutColestrol = (EditText) findViewById(R.id.editTextColestrol);
        textInputLayoutCreatina = (EditText) findViewById(R.id.editTextCreatinina);
        textInputLayoutAcidoUrico = (EditText) findViewById(R.id.editTextAcidoUrico);
        textInputLayoutUreia = (EditText) findViewById(R.id.editTextUreia);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void Cancelar(View view) {
        Toast.makeText(this, R.string.Cancelar, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void Guardar(View view) {
        String diaAnalise = textInputLayoutDia.getText().toString();
        if(diaAnalise.trim().length() == 0){
            textInputLayoutDia.setError(getString(R.string.message_required));
            textInputLayoutDia.requestFocus();
            return;
        }else  if (diaAnalise.length() != 10 || diaAnalise.charAt(2) != '/' || diaAnalise.charAt(5) != '/') {
            textInputLayoutDia.setError(getString(R.string.Validar_data));
            textInputLayoutDia.requestFocus();
            return;
        }
        String[] dataSeparada = diaAnalise.split("/");

        int Dia = Integer.parseInt(dataSeparada[0]);
        int Mes = Integer.parseInt(dataSeparada[1]);
        int Ano = Integer.parseInt(dataSeparada[2]);
        if(Dia > 30 && (Mes == 6 || Mes == 4 || Mes == 9 || Mes == 11)  ) {

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
        }

        String diabetes = textInputLayoutAcucar.getText().toString();
        double Diabetes;

        String Colestrol = textInputLayoutColestrol.getText().toString();
        double colestrol;

        String Creatina = textInputLayoutCreatina.getText().toString();
        double creatina;

        String AcidoUrico = textInputLayoutAcidoUrico.getText().toString();
        double acidoUrico;


        String Ureia = textInputLayoutUreia.getText().toString();
        double ureia;


        if (diabetes.trim().length() == 0) {
            textInputLayoutAcucar.setError(getString(R.string.message_required));
            textInputLayoutAcucar.requestFocus();
            return;
        }
        if (Colestrol.trim().length() == 0) {
            textInputLayoutColestrol.setError(getString(R.string.message_required));
            textInputLayoutColestrol.requestFocus();
            return;
        }
        if (Creatina.trim().length() == 0) {
            textInputLayoutCreatina.setError(getString(R.string.message_required));
            textInputLayoutCreatina.requestFocus();
            return;
        }
        if (AcidoUrico.trim().length() == 0) {
            textInputLayoutAcidoUrico.setError(getString(R.string.message_required));
            textInputLayoutAcidoUrico.requestFocus();
            return;
        }
        if (Ureia.trim().length() == 0) {
            textInputLayoutUreia.setError(getString(R.string.message_required));
            textInputLayoutUreia.requestFocus();
            return;
        }
        try{
            Diabetes = Double.parseDouble(diabetes);
            colestrol = Double.parseDouble(Colestrol);
            creatina = Double.parseDouble(Creatina);
            acidoUrico = Double.parseDouble(AcidoUrico);
            ureia = Double.parseDouble(Ureia);
        }catch (NumberFormatException e){
            return;
        }
        Analise analise = new Analise();

        analise.setDia(diaAnalise);
        analise.setdiabetes(Diabetes);
        analise.setColestrol(colestrol);
        analise.setCreatina(creatina);
        analise.setAcidoUrico(acidoUrico);
        analise.setUreia(ureia);

        try {
            getContentResolver().insert(AMinhaSaudeContentProvider.ENDERECO_ANALISES,analise.getContentValues());

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
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_ANALISES, null, this);

        super.onResume();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_ANALISES, BdTabelaAnalises.TODAS_COLUNAS, null, null, BdTabelaAnalises.Dia
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
