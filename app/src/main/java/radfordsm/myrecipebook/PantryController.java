package radfordsm.myrecipebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by San on 8/23/2016.
 */
public class PantryController {

    private Context our_context;
    private SQLiteDatabase database;
    private PantryHelper pantry_helper;
    static final int NUMBER_OF_COLUMNS = 24;


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


    public Cursor fetch() {
        String[] columns = new String[] { PantryHelper._id, PantryHelper.meats,
                PantryHelper.poultry, PantryHelper.fish, PantryHelper.fruits,
                PantryHelper.vegetables, PantryHelper.dairy, PantryHelper.breads, PantryHelper.jams,
                PantryHelper.sauces, PantryHelper.spices, PantryHelper.mixes, PantryHelper.dressings, PantryHelper.oils,
                PantryHelper.basics, PantryHelper.canned, PantryHelper.sweets, PantryHelper.chips, PantryHelper.crackers,
                PantryHelper.cereals, PantryHelper.snacks, PantryHelper.drinks, PantryHelper.alcohol, PantryHelper.other};
        Cursor cursor = database.query(PantryHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
    public Cursor getX(String column_name){
        String[] columns = new String[] {}
        return cursor;
    }*/

    public int update(String id, String meats, String poultry, String fish, String fruits,
                      String vegetables, String dairy, String breads, String jams,
                      String sauces, String spices, String mixes, String dressings, String oils,
                      String basics, String canned, String sweets, String chips, String crackers,
                      String cereals, String snacks, String drinks, String alcohol, String other){

        ContentValues cv = new ContentValues();
        cv.put(PantryHelper._id,id);
        cv.put(PantryHelper.meats,meats);
        cv.put(PantryHelper.poultry,poultry);
        cv.put(PantryHelper.fish,fish);
        cv.put(PantryHelper.fruits,fruits);
        cv.put(PantryHelper.vegetables,vegetables);
        cv.put(PantryHelper.dairy,dairy);
        cv.put(PantryHelper.breads,breads);
        cv.put(PantryHelper.jams,jams);
        cv.put(PantryHelper.sauces,sauces);
        cv.put(PantryHelper.spices,spices);
        cv.put(PantryHelper.mixes,mixes);
        cv.put(PantryHelper.dressings,dressings);
        cv.put(PantryHelper.oils,oils);
        cv.put(PantryHelper.basics,basics);
        cv.put(PantryHelper.canned,canned);
        cv.put(PantryHelper.sweets,sweets);
        cv.put(PantryHelper.chips,chips);
        cv.put(PantryHelper.crackers,crackers);
        cv.put(PantryHelper.cereals,cereals);
        cv.put(PantryHelper.snacks,snacks);
        cv.put(PantryHelper.drinks,drinks);
        cv.put(PantryHelper.alcohol,alcohol);
        cv.put(PantryHelper.other,other);


        int i = database.update(PantryHelper.TABLE_NAME, cv,
                PantryHelper._id + " = '" + id + "'", null);
        return i;
    }

    public void delete(int id) {
        database.delete(PantryHelper.TABLE_NAME, PantryHelper._id + " = '" + id + "'", null);
    }


    public void insert(String[] v) {

        ContentValues cv = new ContentValues();
        for(String s: v){
            Log.i("values : ", s);
        }
        cv.put(PantryHelper._id, v[0]);
        cv.put(PantryHelper.meats, v[1]);
        cv.put(PantryHelper.poultry, v[2]);
        cv.put(PantryHelper.fish, v[3]);
        cv.put(PantryHelper.fruits, v[4]);
        cv.put(PantryHelper.vegetables, v[5]);
        cv.put(PantryHelper.dairy, v[6]);
        cv.put(PantryHelper.breads, v[7]);
        cv.put(PantryHelper.jams, v[8]);
        cv.put(PantryHelper.sauces, v[9]);
        cv.put(PantryHelper.spices, v[10]);
        cv.put(PantryHelper.mixes, v[11]);
        cv.put(PantryHelper.dressings, v[12]);
        cv.put(PantryHelper.oils, v[13]);
        cv.put(PantryHelper.basics, v[14]);
        cv.put(PantryHelper.canned, v[15]);
        cv.put(PantryHelper.sweets, v[16]);
        cv.put(PantryHelper.chips, v[17]);
        cv.put(PantryHelper.crackers, v[18]);
        cv.put(PantryHelper.cereals, v[19]);
        cv.put(PantryHelper.snacks, v[20]);
        cv.put(PantryHelper.drinks, v[21]);
        cv.put(PantryHelper.alcohol, v[22]);
        cv.put(PantryHelper.other, v[23]);
        Log.i("Vals ", String.valueOf(cv));
        database.insert(PantryHelper.TABLE_NAME, null, cv);
    }

    public String[] getColumnNames(){
        Cursor cursor = database.rawQuery("SELECT * FROM " + PantryHelper.TABLE_NAME + " LIMIT 1", null);
        String[] colNames = cursor.getColumnNames();
        return colNames;
    }

    public int getNumberOfColumns(){
        return NUMBER_OF_COLUMNS;
    }
}


