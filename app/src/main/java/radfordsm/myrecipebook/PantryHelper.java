package radfordsm.myrecipebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by San on 8/23/2016.
 */
public class PantryHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "Pantry";
    public static final String _id = "_id";
    //public static final String name = "name";
    public static final String meats = "Meats";
    public static final String poultry = "Poultry";
    public static final String fish = "Fish";
    public static final String fruits = "Fruits";
    public static final String vegetables = "Vegetables";
    public static final String dairy = "Dairy";
    public static final String breads = "Breads";
    public static final String jams = "Jams";
    public static final String sauces = "Sauces";
    public static final String spices = "Spices";
    public static final String mixes = "Mixes";
    public static final String dressings = "Dressings";
    public static final String oils = "Oils";
    public static final String basics = "Basics";
    public static final String canned = "Canned";
    public static final String sweets = "Sweets";
    public static final String chips = "Chips";
    public static final String crackers = "Crackers";
    public static final String cereals = "Cereals";
    public static final String snacks = "Snacks";
    public static final String drinks = "Drinks";
    public static final String alcohol = "Alcohol";
    public static final String other = "Other";

    static final String DB_NAME = "Pantry.DB";
    static final int DB_VERSION = 6;


    public PantryHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PANTRY_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + _id + " TEXT PRIMARY KEY, " + meats
                + " TEXT, " + poultry + " TEXT, " + fish + " TEXT, " + fruits + " TEXT, "
                + vegetables + " TEXT, " + dairy + " TEXT, " + breads + " TEXT, " + jams + " TEXT, "
                + sauces + " TEXT, " + spices + " TEXT, " + mixes + " TEXT, " + dressings + " TEXT, "
                + oils + " TEXT, " + basics + " TEXT, " + canned + " TEXT, "
                + sweets + " TEXT, " + chips + " TEXT, " + crackers + " TEXT, " + cereals + " TEXT, "
                + snacks + " TEXT, " + drinks + " TEXT, " + alcohol + " TEXT, " + other + " TEXT"
                + ");";


        db.execSQL(CREATE_PANTRY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
