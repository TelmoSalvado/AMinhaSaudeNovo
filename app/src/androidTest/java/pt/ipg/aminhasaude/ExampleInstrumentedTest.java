package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Before
    public void apagaBaseDeDados(){
        getAppContext().deleteDatabase(BdOpenHelper.NOME_BASE_DADOS);
    }
    @Test
    public void criaBdSaude() {
        // Context of the app under test.
        Context appContext = getAppContext();

        BdOpenHelper openHelper = new BdOpenHelper(appContext);

        SQLiteDatabase db = openHelper.getReadableDatabase();

        assertTrue(db.isOpen());
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

}
