package radfordsm.myrecipebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by San on 8/23/2016.
 */
public class PantryHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "Pantry";
    static final String _id = "_id";
    //static final String name = "name";
    static final String meats = "Meats";
    static final String poultry = "Poultry";
    static final String fish = "Fish";
    static final String fruits = "Fruits";
    static final String vegetables = "Vegetables";
    static final String dairy = "Dairy";
    static final String breads = "Breads";
    static final String jams = "Jams";
    static final String sauces = "Sauces";
    static final String spices = "Spices";
    static final String mixes = "Mixes";
    static final String dressings = "Dressings";
    static final String oils = "Oils";
    static final String basic_ingredients = "Basic Ingredients";
    static final String canned_foods = "Canned Foods";
    static final String sweets = "Sweets";
    static final String chips = "Chips";
    static final String crackers = "Crackers";
    static final String cereals = "Cereals";
    static final String snacks = "Snacks";
    static final String drinks = "Drinks";
    static final String alcohol = "Alcohol";
    static final String other = "Other";

    static final String DB_NAME = "Pantry.DB";
    static final int DB_VERSION = 1;


    public PantryHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + _id + " TEXT PRIMARY KEY, " + meats
                + " TEXT, " + poultry + " TEXT, " + fish + " TEXT, " + fruits + " TEXT, "
                + vegetables + " TEXT, " + dairy + " TEXT, " + breads + " TEXT, " + jams + " TEXT, "
                + sauces + " TEXT, " + spices + " TEXT, " + mixes + " TEXT, " + dressings + " TEXT, "
                + oils + " TEXT, " + basic_ingredients + " TEXT, " + canned_foods + " TEXT, "
                + sweets + " TEXT, " + chips + " TEXT, " + crackers + " TEXT, " + cereals + " TEXT, "
                + snacks + " TEXT, " + drinks + " TEXT, " + alcohol + " TEXT, " + other + " TEXT, "
                + ");";


        db.execSQL(CREATE_ACCOUNTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
