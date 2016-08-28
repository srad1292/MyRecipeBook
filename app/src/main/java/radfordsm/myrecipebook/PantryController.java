package radfordsm.myrecipebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
                PantryHelper.basic_ingredients, PantryHelper.canned_foods, PantryHelper.sweets, PantryHelper.chips, PantryHelper.crackers,
                PantryHelper.cereals, PantryHelper.snacks, PantryHelper.drinks, PantryHelper.alcohol, PantryHelper.other, };
        Cursor cursor = database.query(PantryHelper.TABLE_NAME, columns, null,
                null, null, null, null);
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
                      String basic_ingredients, String canned_foods, String sweets, String chips, String crackers,
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
        cv.put(PantryHelper.basic_ingredients,basic_ingredients);
        cv.put(PantryHelper.canned_foods,canned_foods);
        cv.put(PantryHelper.sweets,sweets);
        cv.put(PantryHelper.chips,chips);
        cv.put(PantryHelper.chips,crackers);
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


    public void insert(String[] values) {

        ContentValues cv = new ContentValues();
        cv.put(PantryHelper._id, values[0]);
        cv.put(PantryHelper.meats, values[1]);
        cv.put(PantryHelper.poultry, values[2]);
        cv.put(PantryHelper.fish, values[3]);
        cv.put(PantryHelper.fruits, values[4]);
        cv.put(PantryHelper.vegetables, values[5]);
        cv.put(PantryHelper.dairy, values[6]);
        cv.put(PantryHelper.breads, values[7]);
        cv.put(PantryHelper.jams, values[8]);
        cv.put(PantryHelper.sauces, values[9]);
        cv.put(PantryHelper.spices, values[10]);
        cv.put(PantryHelper.mixes, values[11]);
        cv.put(PantryHelper.dressings, values[12]);
        cv.put(PantryHelper.oils, values[13]);
        cv.put(PantryHelper.basic_ingredients, values[14]);
        cv.put(PantryHelper.canned_foods, values[15]);
        cv.put(PantryHelper.sweets, values[16]);
        cv.put(PantryHelper.chips, values[17]);
        cv.put(PantryHelper.chips, values[18]);
        cv.put(PantryHelper.cereals, values[19]);
        cv.put(PantryHelper.snacks, values[20]);
        cv.put(PantryHelper.drinks, values[21]);
        cv.put(PantryHelper.alcohol, values[22]);
        cv.put(PantryHelper.other, values[23]);

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


