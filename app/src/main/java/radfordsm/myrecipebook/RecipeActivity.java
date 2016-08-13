package radfordsm.myrecipebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.recipes_button);
        setContentView(R.layout.activity_recipe);
    }
}
