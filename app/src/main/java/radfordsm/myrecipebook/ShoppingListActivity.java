package radfordsm.myrecipebook;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_edit:
                //User pressed edit
                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("hi");
                alertDialog.setMessage("this is my app");
                alertDialog.show();
                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        alertDialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 5000); // the timer will count 5 seconds....

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
