package pt.ipg.aminhasaude;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarTratamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tratamento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Guardar(View view){
        EditText editTextmedicamento = (EditText) findViewById(R.id.EditTextMedicamento);
        String medicamento = editTextmedicamento.getText().toString();

        EditText editTextHora = (EditText) findViewById(R.id.editTextHoraComeco);
        String hora = editTextHora.getText().toString();

        EditText editTextHoraATomar = (EditText) findViewById(R.id.EditTextHorasAtomar);
        String horaatomar = editTextHoraATomar.getText().toString();

        EditText editTextDias = (EditText) findViewById(R.id.EditTextDias);
        String dias = editTextDias.getText().toString();


        EditText editTextDoenca = (EditText) findViewById(R.id.EditTextDoenca);
        String doenca = editTextDoenca.getText().toString();

        if (doenca.trim().length() == 0){
            editTextDoenca.setError(getString(R.string.message_required));
            editTextDoenca.requestFocus();
            return;
        }

        if (medicamento.trim().length() == 0) {
            editTextmedicamento.setError(getString(R.string.message_required));
            editTextmedicamento.requestFocus();
            return;
        }
        if (hora.length() != 5 || hora.charAt(2) != ':' ) {
            editTextHora.setError(getString(R.string.validar_hora));
            editTextHora.requestFocus();
            return;
        }
        if (horaatomar.length() != 5 || horaatomar.charAt(2) != ':' ) {
            editTextHoraATomar.setError(getString(R.string.validar_hora));
            editTextHoraATomar.requestFocus();
            return;
        }
        if ((dias.trim().length() == 0) )  {
            editTextDias.setError(getString(R.string.message_required));
            editTextDias.requestFocus();
            return;
        }


        Toast.makeText(this, "Guardado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void Cancelar(View view){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
