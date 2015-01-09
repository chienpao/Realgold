package app.wordpress.me.synology.chienpao.realgold;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private JsoupThread jsoupThread;
    private Handler handler;
    private TextView textView_sellPrice;
    private TextView textView_buyPrice;
    private TextView textView_updateTime;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_sellPrice = (TextView)findViewById(R.id.textView_sellPrice);
        textView_buyPrice = (TextView)findViewById(R.id.textView_buyPrice);
        textView_updateTime = (TextView)findViewById(R.id.textView_updateTime);

        button = (Button)findViewById(R.id.button);

        parseHTML();
        handleText();
    }

    public void parseHTML() {
        jsoupThread = new JsoupThread();
        jsoupThread.start();
    }

    public void handleText(){
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Log.i("Pao", jsoupThread.getState().toString());
                    textView_sellPrice.setText(JsoupThread.sellPrice);
                    textView_buyPrice.setText(JsoupThread.buyPrice);
                    textView_updateTime.setText(JsoupThread.updateTime);
                    if(jsoupThread.running == false)
                        break;
                }
            }
        });

    }

    public void setText(View view){
        Log.i("Pao", "Button Click");
        parseHTML();
        handleText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
