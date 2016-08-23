package radfordsm.myrecipebook;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by San on 8/23/2016.
 */
public class PantryController {

    private Context our_context;
    private SQLiteDatabase database;
    private PantryHelper pantry_helper;

    public PantryController(Context c){
        our_context = c;
    }

    public PantryController open() throws SQLException {
        pantry_helper = new PantryHelper(our_context);
        database = pantry_helper.getWritableDatabase();
        return this;

    }

    public void close(){
        pantry_helper.close();
    }
}
