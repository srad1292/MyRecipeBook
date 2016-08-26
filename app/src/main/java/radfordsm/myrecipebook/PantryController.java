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

    public int update(String id, String meats, String poultry, String fish, String fruits,
                      String vegetables, String dairy, String breads, String jams,
                      String sauces, String spices, String mixes, String dressings, String oils,
                      String basic_ingredients, String canned_foods, String sweets, String chips, String crackers,
                      String cereals, String snacks, String drinks, String alcohol, String other){

        ContentValues cv = new ContentValues();

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
}


