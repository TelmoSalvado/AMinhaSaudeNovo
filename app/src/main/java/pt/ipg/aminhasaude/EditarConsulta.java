package pt.ipg.aminhasaude;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarConsulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_consulta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Guardar(View view){
        EditText EditTextDia = (EditText) findViewById(R.id.EditTextDia);
        String dia = EditTextDia.getText().toString();

        EditText editTextHora = (EditText) findViewById(R.id.EditTextHora);
        String hora = editTextHora.getText().toString();

        EditText editTextLocal = (EditText) findViewById(R.id.EditTextLocal);
        String local = editTextLocal.getText().toString();

        EditText editTextMotivo = (EditText) findViewById(R.id.EditTextLocal);
        String motivo = editTextMotivo.getText().toString();


        EditText editTextmedico = (EditText) findViewById(R.id.EditTextMedico);
        String Medico = editTextmedico.getText().toString();

        if (dia.trim().length() == 0){
            EditTextDia.setError(getString(R.string.message_required));
            EditTextDia.requestFocus();
            return;
        }

        if (hora.trim().length() == 0) {
            editTextHora.setError(getString(R.string.message_required));
            editTextHora.requestFocus();
            return;
        }
        if (local.trim().length()  == 0) {
            editTextLocal.setError(getString(R.string.message_required));
            editTextLocal.requestFocus();
            return;
        }
        if (motivo.trim().length() == 0) {
            editTextMotivo.setError(getString(R.string.message_required));
            editTextMotivo.requestFocus();
            return;
        }



        Toast.makeText(this, getString(R.string.Guardar), Toast.LENGTH_SHORT).show();
        finish();
    }
    public void Cancelar(View view){
        Toast.makeText(this, getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
        finish();
    }
}
