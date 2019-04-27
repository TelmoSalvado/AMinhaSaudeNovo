package pt.ipg.aminhasaude;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarAnalises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_analises);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Cancelar(View view){
        Toast.makeText(this, R.string.Cancelar, Toast.LENGTH_SHORT).show();
        finish();
    }
    public void Guardar(View view){
        ValidarDados();
        Toast.makeText(this, R.string.Guardar, Toast.LENGTH_SHORT).show();

    }
    public void ValidarDados() {
        EditText editTextDia = (EditText) findViewById(R.id.editTextDataAnalises);
        String diaAnalise = editTextDia.getText().toString();

        EditText editTextDiabetes = (EditText) findViewById(R.id.editTextAcucar);
        String diabetes = editTextDiabetes.getText().toString();

        EditText editTextColestrol = (EditText) findViewById(R.id.editTextColestrol);
        String Colestrol = editTextColestrol.getText().toString();

        EditText editTextCreatina = (EditText) findViewById(R.id.editTextCreatinina);
        String Creatina = editTextCreatina.getText().toString();

        EditText editTextAcido = (EditText) findViewById(R.id.editTextAcidoUrico);
        String AcidoUrico = editTextAcido.getText().toString();

        EditText editTextUreia = (EditText) findViewById(R.id.editTextUreia);
        String Ureia = editTextUreia.getText().toString();

        if (diaAnalise.trim().length() == 0) {
            editTextDia.setError(getString(R.string.message_required));
            editTextDia.requestFocus();
            return;
        }
        if (diabetes.trim().length() == 0) {
            editTextDiabetes.setError(getString(R.string.message_required));
            editTextDiabetes.requestFocus();
            return;
        }
        if (Colestrol.trim().length() == 0) {
            editTextColestrol.setError(getString(R.string.message_required));
            editTextColestrol.requestFocus();
            return;
        }
        if (Creatina.trim().length() == 0) {
            editTextCreatina.setError(getString(R.string.message_required));
            editTextCreatina.requestFocus();
            return;
        }
        if (AcidoUrico.trim().length() == 0) {
            editTextAcido.setError(getString(R.string.message_required));
            editTextAcido.requestFocus();
            return;
        }
        if (Ureia.trim().length() == 0) {
            editTextUreia.setError(getString(R.string.message_required));
            editTextUreia.requestFocus();
            return;
        }

        finish();
    }
}
