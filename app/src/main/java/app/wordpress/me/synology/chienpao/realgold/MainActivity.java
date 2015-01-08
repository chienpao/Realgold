package app.wordpress.me.synology.chienpao.realgold;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseHTML();
    }

    public void parseHTML(){

        new Thread(new Runnable(){
            @Override
            public void run() {
                URL url = null;
                // TODO Auto-generated method stub

                    Document doc = null;
                    try {
                        doc = Jsoup.connect("http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm").get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Elements test = doc.getElementsByClass("goldName0");
                    Log.i("Pao", test.text());

            }
        }).start();
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
