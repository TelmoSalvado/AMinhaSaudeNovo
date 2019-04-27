package pt.ipg.aminhasaude;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class VerConsultas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_consultas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Editar(View view){
        Intent intent = new Intent(this, EditarConsulta.class);
        startActivity(intent);
    }
    public void Eliminar(View view){
        Intent intent = new Intent(this, EliminarConsulta.class);
        startActivity(intent);
    }
}