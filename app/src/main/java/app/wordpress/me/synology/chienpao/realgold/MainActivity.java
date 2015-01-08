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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    public String temp = "";
    private Thread mThread;
    private Handler handler;
    private TextView textView;
    private Button button;
    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.test);
        button = (Button)findViewById(R.id.button);

        parseHTML();
        handleText();
    }

    public void parseHTML() {

        running = true;
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element id = doc.getElementById("GoldBankBookForTWD");
                Elements tables = id.getElementsByClass("decimal");

                temp = tables.text().substring(12,16);
                running = false;
                Log.i("Pao", temp);
            }
        });
        mThread.start();

    }

    public void handleText(){
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Log.i("Pao", mThread.getState().toString());
                    textView.setText(temp);
                    if(running == false)
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
