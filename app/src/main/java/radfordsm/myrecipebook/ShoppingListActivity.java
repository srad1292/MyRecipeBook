package radfordsm.myrecipebook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;




public class ShoppingListActivity extends AppCompatActivity {

    final int RESULT = 1;
    private TextView textField = (TextView) findViewById(R.id.shopping_list_text);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.shopping_list_toolbar);
        myToolbar.setTitle(R.string.shopping_list_button);
        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopping_list_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_shopping:
                Intent intent = new Intent(this, EditShoppingListActivity.class);

                if(textField.getText().toString().length() > 0){
                    intent.putExtra("text",textField.getText());
                }

                startActivityForResult(intent,RESULT);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //check that activity wasn't cancelled
        if(!data.getStringExtra("value").equals("3")){
            textField.setText(data.getStringExtra("newText"));
        }
    }
}
