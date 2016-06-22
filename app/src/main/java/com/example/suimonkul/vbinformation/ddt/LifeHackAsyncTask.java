package com.example.suimonkul.vbinformation.ddt;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Suimonkul on 15.06.2016.
 */

public class LifeHackAsyncTask extends AsyncTask<String, String, Elements> {

    String title;
    String more;
    TextView moreInformation;
    TextView h1title;
    Intent intent;
    Activity activity;
    Document document = null;
    Elements text;

    public LifeHackAsyncTask(Activity activity, TextView moreInformation, TextView h1title) {
        this.activity = activity;
        this.moreInformation = moreInformation;
        this.h1title = h1title;
    }

    @Override
    protected Elements doInBackground(String... params) {

        intent = activity.getIntent();
        String intentUrl = intent.getStringExtra("url");

        try {
            Log.d("moreLogUrl", intentUrl);
            document = Jsoup.connect(intentUrl).get();

            text = document.select("div#topic");

            title = text.select("p").text();
            more = text.select("div.topic-text").text();
            Log.d("moreLogPeople", "1=1==11=1=" + more);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    protected void onPostExecute(Elements elements) {
        super.onPostExecute(elements);
        h1title.setText(title);
//        moreInformation.setText(more);
    }
}
