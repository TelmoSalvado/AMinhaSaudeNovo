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

public class EliminarConsulta extends AppCompatActivity {
    private Uri enderecoConsultaApagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_consulta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        EditText editTextData= (EditText) findViewById(R.id.editText6);
        EditText editTextHora = (EditText) findViewById(R.id.editText7);
        EditText editTextLocal = (EditText) findViewById(R.id.editText8);
        EditText editTextMotivo = (EditText) findViewById(R.id.editText9);
        EditText editTextMedico = (EditText) findViewById(R.id.editText10);

        Intent intent = getIntent();
        long idConsulta = intent.getLongExtra(VerConsultas.ID_CONSULTA,-1);

        if(idConsulta == -1){
            Toast.makeText(this, "Erro: não foi possivel apagar a Consulta", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }
        enderecoConsultaApagar = Uri.withAppendedPath(AMinhaSaudeContentProvider.ENDERECO_CONSULTA, String.valueOf(idConsulta));

        Cursor cursor = getContentResolver().query(enderecoConsultaApagar, BdTabelaConsulta.TODAS_COLUNAS, null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel apagar a Consulta!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Consulta consulta = Consulta.fromCursor(cursor);

        editTextData.setText(consulta.getdia());
        editTextHora.setText(consulta.getHora());
        editTextLocal.setText(consulta.getlocal());
        editTextMotivo.setText(consulta.getmotivo());
        editTextMedico.setText(consulta.getMedico());
    }
    public void Eliminar(View view){
        int consultaApagados = getContentResolver().delete(enderecoConsultaApagar, null, null);

        if (consultaApagados == 1) {
            Toast.makeText(this, getString(R.string.Eliminar), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro: Não foi possível eliminar a Consulta", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, getString(R.string.Eliminar), Toast.LENGTH_SHORT).show();
        finish();
    }
    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }
}
