package com.example.suimonkul.vbinformation.ddt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Suimonkul on 15.06.2016.
 */

public class LastAsyncTask extends AsyncTask<String, String, Elements> {

    String title;
    String more;
    TextView moreInformation;
    TextView h1title;
    Intent intent;
    Activity activity;
    Document document = null;
    Elements text;
    ImageView im1, im2, im3, im4, im5;
    ArrayList<ImageView> images;

    public LastAsyncTask(Activity activity, TextView moreInformation, TextView h1title, ImageView im1) {
        this.activity = activity;
        this.moreInformation = moreInformation;
        this.h1title = h1title;
        this.im1 = im1;
//        this.images = new ArrayList<ImageView>();
//
//        this.images.add(im1);
//        this.images.add(im2);
//        this.images.add(im3);
//        this.images.add(im4);
//        this.images.add(im5);
    }

    @Override
    protected Elements doInBackground(String... params) {

        intent = activity.getIntent();
        String intentUrl = intent.getStringExtra("url");

        Log.d("myLog", intentUrl);
        try {
            document = Jsoup.connect(intentUrl).get();

            Log.d("Adme", "" + document.select("div.container"));
            text = document.select("div.container");

            title = document.select("h1").text();
            more = text.select("div.content-box").text();

            Log.d("moreLog", title);
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
//        Picasso.with(activity).load(text.select("img.aligncenter").attr("src")).into(im1);

//        Elements imgs = text.select("img");
//
//        for (int i = 0; i < 5; i++) {
//            Picasso.with(activity).load(imgs.get(i).attr("src")).into(this.images.get(i));
//        }

    }
}
