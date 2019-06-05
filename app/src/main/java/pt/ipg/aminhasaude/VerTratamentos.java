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

public class VerTratamentos extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSO_LOADER_TRATAMENTOS = 0;

    private RecyclerView recyclerViewTratamentos;
    private AdaptadorTratamentos adaptadorTratamentos;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tratamentos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_TRATAMENTOS,null,this);

        recyclerViewTratamentos = (RecyclerView) findViewById(R.id.recyclerViewTratamentos);
        adaptadorTratamentos = new AdaptadorTratamentos(this);
        recyclerViewTratamentos.setAdapter(adaptadorTratamentos);
        recyclerViewTratamentos.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void atualizaOpcoesMenu(){
        Tratamento tratamento = adaptadorTratamentos.getTratamentoSelecionado();

        boolean mostraAlterarEliminar = (tratamento != null);

        menu.findItem(R.id.action_alterar).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_eliminar).setVisible(mostraAlterarEliminar);
    }

    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_TRATAMENTOS, null, this);

        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tratamentos, menu);

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
            Intent intent = new Intent(this, Novo_Tratamento.class);

            startActivity(intent);

            return true;
        } else if (id == R.id.action_alterar) {
            Toast.makeText(this, "Alterar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_eliminar) {
            Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, AMinhaSaudeContentProvider.ENDERECO_TRATAMENTOS, BdTabelaTratamento.TODAS_COLUNAS, null, null, BdTabelaTratamento.Nome_Medicamento
        );

        return cursorLoader;
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Snackbar.make(recyclerViewTratamentos,"Qualquer coisa:" +data.getCount(),Snackbar.LENGTH_INDEFINITE).show();


        adaptadorTratamentos.setCursor(data);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorTratamentos.setCursor(null);
    }
}
