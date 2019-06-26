package pt.ipg.aminhasaude;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class VerConsultas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    public static final String ID_CONSULTA = "ID_CONSULTA";
    private static final int ID_CURSO_LOADER_CONSULTA = 0;

    private RecyclerView recyclerViewConsulta;
    private AdaptadorConsulta adaptadorConsulta;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_consultas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_CONSULTA,null,this);

        recyclerViewConsulta = (RecyclerView) findViewById(R.id.recyclerViewConsulta);
        adaptadorConsulta = new AdaptadorConsulta(this);
        recyclerViewConsulta.setAdapter(adaptadorConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void atualizaOpcoesMenu(){
       Consulta consulta = adaptadorConsulta.getConsultaSelecionado();

        boolean mostraAlterarEliminar = (consulta != null);

        menu.findItem(R.id.action_alterar).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_eliminar).setVisible(mostraAlterarEliminar);
    }
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_CONSULTA, null, this);

        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta, menu);

        this.menu = menu;

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inserir) {
            Intent intent = new Intent(this, Nova_Consulta.class);

            startActivity(intent);

            return true;
        } else if (id == R.id.action_alterar) {
            Intent intent = new Intent(this, EditarConsulta.class);
            intent.putExtra(ID_CONSULTA, adaptadorConsulta.getConsultaSelecionado().getId());
            startActivity(intent);

            return true;
        } else if (id == R.id.action_eliminar) {
            Intent intent = new Intent(this, EliminarConsulta.class);
            intent.putExtra(ID_CONSULTA, adaptadorConsulta.getConsultaSelecionado().getId());
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_CONSULTA, BdTabelaConsulta.TODAS_COLUNAS, null, null, BdTabelaConsulta.Dia
        );

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Snackbar.make(recyclerViewConsulta,"Guardados:" +data.getCount(),Snackbar.LENGTH_INDEFINITE).show();


        adaptadorConsulta.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorConsulta.setCursor(null);
    }
}
