package app.wordpress.me.synology.chienpao.realgold;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by pao on 1/9/15.
 */
public class JsoupThread extends Thread{

    static public String temp = "";
    static public boolean running;

    @Override
    public void run() {
        super.run();
        running = true;
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
}
