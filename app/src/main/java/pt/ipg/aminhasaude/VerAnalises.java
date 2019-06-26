package pt.ipg.aminhasaude;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

public class VerAnalises extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ID_ANALISES = "ID_ANALISES";
    private static final int ID_CURSO_LOADER_ANALISES = 0;

    private RecyclerView recyclerViewAnalises;
    private AdaptadorAnalises adaptadorAnalises;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_analises);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_ANALISES,null,this);

        recyclerViewAnalises = (RecyclerView) findViewById(R.id.recyclerViewAnalises);
        adaptadorAnalises = new AdaptadorAnalises(this);
        recyclerViewAnalises.setAdapter(adaptadorAnalises);
        recyclerViewAnalises.setLayoutManager(new LinearLayoutManager(this));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void atualizaOpcoesMenu(){
        Analise analise = adaptadorAnalises.getAnaliseSelecionado();

        boolean mostraAlterarEliminar = (analise != null);

        menu.findItem(R.id.action_editar).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_eliminar).setVisible(mostraAlterarEliminar);
    }

    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_ANALISES, null, this);

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_analises, menu);

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
            Intent intent = new Intent(this, NovosResultadosAnalises.class);

            startActivity(intent);

            return true;
        } else if (id == R.id.action_editar) {
            Intent intent = new Intent(this, EditarAnalises.class);
            intent.putExtra(ID_ANALISES, adaptadorAnalises.getAnaliseSelecionado().getId());
            startActivity(intent);

            Toast.makeText(this, "Alterar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_eliminar) {
            Intent intent = new Intent(this, EliminarAnalises.class);
            intent.putExtra(ID_ANALISES, adaptadorAnalises.getAnaliseSelecionado().getId());
            startActivity(intent);

            Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_ANALISES, BdTabelaAnalises.TODAS_COLUNAS, null, null, BdTabelaAnalises.Dia
        );

        return cursorLoader;

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Snackbar.make(recyclerViewAnalises,"Guardados:" +data.getCount(),Snackbar.LENGTH_INDEFINITE).show();


        adaptadorAnalises.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorAnalises.setCursor(null);
    }
}
