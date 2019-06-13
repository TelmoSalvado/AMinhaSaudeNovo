package pt.ipg.aminhasaude;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EliminarAnalises extends AppCompatActivity {
    private Uri enderecoAnaliseApagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_analises);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editTextDataAnalises = (EditText) findViewById(R.id.editText11);
        EditText editTextAcucar = (EditText) findViewById(R.id.editText12);
        EditText editTextColestrol = (EditText) findViewById(R.id.editText13);
        EditText editTextCreatinina = (EditText) findViewById(R.id.editText14);
        EditText editTextAcidoUrico = (EditText) findViewById(R.id.editText15);
        EditText editTextUreia = (EditText) findViewById(R.id.editText16);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        long idAnalises = intent.getLongExtra(VerAnalises.ID_ANALISES,-1);

        if(idAnalises == -1){
            Toast.makeText(this, "Erro: não foi possivel apagar a Analise!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }
        enderecoAnaliseApagar = Uri.withAppendedPath(AMinhaSaudeContentProvider.ENDERECO_ANALISES, String.valueOf(idAnalises));

        Cursor cursor = getContentResolver().query(enderecoAnaliseApagar, BdTabelaAnalises.TODAS_COLUNAS, null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel apagar a Analise!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        Analise analise = Analise.fromCursor(cursor);

        editTextDataAnalises.setText(analise.getDia());
        editTextAcucar.setText(String.valueOf(analise.getdiabetes()));
        editTextColestrol.setText(String.valueOf(analise.getColestrol()));
        editTextCreatinina.setText(String.valueOf(analise.getCreatina()));
        editTextAcidoUrico.setText(String.valueOf(analise.getAcidoUrico()));
        editTextUreia.setText(String.valueOf(analise.getUreia()));

    }
    public void Eliminar(View view){
        int analisesApagados = getContentResolver().delete(enderecoAnaliseApagar, null, null);

        if (analisesApagados == 1) {
            Toast.makeText(this, getString(R.string.Eliminar), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro: Não foi possível eliminar a Analises", Toast.LENGTH_LONG).show();
        }
        finish();
    }
    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }

}
