package pt.ipg.aminhasaude;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AMinhaSaudeContentProvider extends ContentProvider {
    public static final String AUTHORITY = "pt.ipg.aminhasaude";
    public static final String TRATAMENTOS = "Tratamentos";
    public static final String ANALISES = "Analises";
    public static final String CONSULTA = "Consulta";

    private static final Uri ENDERECO_BASE = Uri.parse("content://" + AUTHORITY);
    public static final Uri ENDERECO_TRATAMENTOS = Uri.withAppendedPath(ENDERECO_BASE, TRATAMENTOS);
    public static final Uri ENDERECO_ANALISES = Uri.withAppendedPath(ENDERECO_BASE,ANALISES);
    public static final Uri ENDERECO_CONSULTA = Uri.withAppendedPath(ENDERECO_BASE, CONSULTA);

    public static final int URI_TRATAMENTOS = 100;
    public static final int URI_TRATAMENTOS_ESPECIFICA = 101;
    public static final int URI_ANALISES = 200;
    public static final int URI_ANALISES_ESPECIFICA = 201;
    public static final int URI_CONSULTA = 300;
    public static final int URI_CONSULTA_ESPECIFICA = 301;

    public static final String UNICO_ITEM = "vnd.android.cursor.item/";
    private static final String MULTIPLOS_ITEMS = "vnd.android.cursor.dir/";

    private BdOpenHelper bdOpenHelper;

   private UriMatcher getUriMatcher(){
       UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

       uriMatcher.addURI(AUTHORITY, TRATAMENTOS, URI_TRATAMENTOS);
       uriMatcher.addURI(AUTHORITY, TRATAMENTOS + "/#", URI_TRATAMENTOS_ESPECIFICA);
       uriMatcher.addURI(AUTHORITY,ANALISES,URI_ANALISES);
       uriMatcher.addURI(AUTHORITY, ANALISES +"/#", URI_ANALISES_ESPECIFICA);
       uriMatcher.addURI(AUTHORITY, CONSULTA, URI_CONSULTA);
       uriMatcher.addURI(AUTHORITY, CONSULTA + "/#", URI_CONSULTA_ESPECIFICA);

       return uriMatcher;
   }

    @Override
    public boolean onCreate() {
       bdOpenHelper = new BdOpenHelper(getContext());
       return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase bd = bdOpenHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_TRATAMENTOS:
                return new BdTabelaTratamento(bd).query(projection,selection,selectionArgs,null, null, sortOrder);
            case URI_TRATAMENTOS_ESPECIFICA:
                return new BdTabelaTratamento(bd).query(projection, BdTabelaTratamento._ID + "=?", new String[]{id}, null, null, null);
            case URI_ANALISES:
                return new BdTabelaAnalises(bd).query(projection,selection,selectionArgs,null,null, sortOrder);
            case URI_ANALISES_ESPECIFICA:
                return new BdTabelaAnalises(bd).query(projection, BdTabelaAnalises._ID + "=?",new String[] {id}, null, null, null);
            case URI_CONSULTA:
                return new BdTabelaConsulta(bd).query(projection,selection,selectionArgs,null,null, sortOrder);
            case URI_CONSULTA_ESPECIFICA:
                return new BdTabelaConsulta(bd).query(projection,BdTabelaConsulta._ID + "=?", new String[] {id},null, null, null);

            default:
                throw new UnsupportedOperationException("Uri inválida (Query)" + uri.toString());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (getUriMatcher().match(uri)) {
            case URI_TRATAMENTOS:
                return MULTIPLOS_ITEMS + TRATAMENTOS;
            case URI_TRATAMENTOS_ESPECIFICA:
                return UNICO_ITEM + TRATAMENTOS;
            case URI_ANALISES:
                return MULTIPLOS_ITEMS + ANALISES;
            case URI_ANALISES_ESPECIFICA:
                return UNICO_ITEM + ANALISES;
            case URI_CONSULTA:
                return MULTIPLOS_ITEMS + CONSULTA;
            case URI_CONSULTA_ESPECIFICA:
                return UNICO_ITEM + CONSULTA;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
       SQLiteDatabase bd = bdOpenHelper.getWritableDatabase();

       long id = -1;

        switch (getUriMatcher().match(uri)){
            case URI_TRATAMENTOS:
                id = new BdTabelaTratamento(bd). insert(values);
                break;
            case URI_ANALISES:
                id = new BdTabelaAnalises(bd).insert(values);
                break;
            case URI_CONSULTA:
                id = new BdTabelaConsulta(bd).insert(values);
                break;
            default:
                throw new UnsupportedOperationException("URI inválida (INSERT):" + uri.toString());
        }
            if(id == -1){
                throw new SQLException("Não foi possivel inserir o registo");
            }

        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = bdOpenHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_TRATAMENTOS_ESPECIFICA:
                return new BdTabelaTratamento(bd).delete(BdTabelaTratamento._ID + "=?", new String[]{id});
            case URI_ANALISES_ESPECIFICA:
                return new BdTabelaAnalises(bd).delete(BdTabelaAnalises._ID + "=?", new String[]{id});
            case URI_CONSULTA_ESPECIFICA:
                return new BdTabelaConsulta(bd).delete(BdTabelaConsulta._ID + "=?", new String[]{id});
            default:
                throw new UnsupportedOperationException("Uri inválida (DELETE): " + uri.toString());
        }

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = bdOpenHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch ((getUriMatcher().match(uri))){
            case URI_TRATAMENTOS_ESPECIFICA:
                return new BdTabelaTratamento(bd).update(values, BdTabelaTratamento._ID + "=?", new String[] {id});
            case URI_ANALISES_ESPECIFICA:
                return new BdTabelaAnalises(bd).update(values,BdTabelaAnalises._ID + "=?", new String[] {id});
            case URI_CONSULTA_ESPECIFICA:
                return new BdTabelaConsulta(bd).update(values, BdTabelaConsulta._ID + "=?", new String[] {id});
            default:
                throw new UnsupportedOperationException("URI inválida (Update): " + uri.toString());
        }
    }
}
