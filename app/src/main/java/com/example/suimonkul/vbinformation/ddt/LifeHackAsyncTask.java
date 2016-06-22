package com.example.suimonkul.vbinformation.ddt;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    ImageView im1;
    Document document = null;
    Elements text;

    public LifeHackAsyncTask(Activity activity, TextView moreInformation, TextView h1title, ImageView im1) {
        this.activity = activity;
        this.moreInformation = moreInformation;
        this.h1title = h1title;
        this.im1 = im1;
    }

    @Override
    protected Elements doInBackground(String... params) {

        intent = activity.getIntent();
        String intentUrl = intent.getStringExtra("url");
        String prefix = "http://www.igromania.ru";
        Log.d("moreLogUrl", prefix+intentUrl);
        try {

            document = Jsoup.connect(prefix+intentUrl).get();

            text = document.select("div.full_block2");

            title = text.select("a.artsectname").text();
            more = text.select("div.awim_container").text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    protected void onPostExecute(Elements elements) {
        super.onPostExecute(elements);
        h1title.setText(title);
        moreInformation.setText(more);
        Picasso.with(activity).load(text.select("img").attr("src")).into(im1);
    }
}
