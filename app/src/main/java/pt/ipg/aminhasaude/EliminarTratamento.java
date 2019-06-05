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

public class EliminarTratamento extends AppCompatActivity {
    private Uri enderecoTratamentoApagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_tratamento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText editTextMedicamento = (EditText) findViewById(R.id.editText2);
        EditText editTextDoenca = (EditText) findViewById(R.id.editText);
        EditText editTextHora = (EditText) findViewById(R.id.editText3);
        EditText editTextHoraATomar = (EditText) findViewById(R.id.editText4);
        EditText editTextDias = (EditText) findViewById(R.id.editText5);

        Intent intent = getIntent();
        long idTratamento = intent.getLongExtra(VerTratamentos.ID_TRATAMENTO,-1);

        if(idTratamento == -1){
            Toast.makeText(this, "Erro: não foi possivel apagar o tratamento!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }
        enderecoTratamentoApagar = Uri.withAppendedPath(AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS, String.valueOf(idTratamento));

        Cursor cursor = getContentResolver().query(enderecoTratamentoApagar, BdTabelaTratamento.TODAS_COLUNAS, null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel apagar o Tratamento!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Tratamento tratamento = Tratamento.fromCursor(cursor);

        editTextDoenca.setText(tratamento.getDoenca());
        editTextMedicamento.setText(tratamento.getMedicamento());
        editTextHora.setText(tratamento.getHora());
        editTextHoraATomar.setText(tratamento.getHoraATomar());
        editTextDias.setText(String.valueOf(tratamento.getdias()));
    }
    public void Eliminar(View view) {
        int TratamentosApagados = getContentResolver().delete(enderecoTratamentoApagar, null, null);

        if (TratamentosApagados == 1) {
            Toast.makeText(this, getString(R.string.Eliminar), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro: Não foi possível eliminar o Tratamento", Toast.LENGTH_LONG).show();
        }
    }
    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }

}
