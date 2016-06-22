package com.example.suimonkul.vbinformation.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.suimonkul.vbinformation.R;
import com.example.suimonkul.vbinformation.adapter.LifeHackAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Suimonkul on 16.06.2016.
 */

public class LifeHackFragment extends AbstractTabsFragment {

    private static final int LAYOUT = R.layout.life;

    public static LifeHackFragment getInstance(Context context) {
        Bundle args = new Bundle();
        LifeHackFragment fragment = new LifeHackFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.peoples_news));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        DataDownloadLife task = new DataDownloadLife();
        task.execute();
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class DataDownloadLife extends AsyncTask<String, String, Elements> {

        LifeHackAdapter adapter;
        Elements listElements;


        @Override
        protected Elements doInBackground(String... params) {

            Document document = null;
            listElements = new Elements();
            String urlSite = "http://kedem.ru/news/";
            try {


                document = Jsoup.connect(urlSite).get();
                listElements = document.select("div.content");
//                Log.d("exMy", ""+document);

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("exMy", "LifeHack");
            }

            return listElements;
        }

        @Override
        protected void onPostExecute(Elements listElements) {
            super.onPostExecute(listElements);
            Log.d("exMy", ""+listElements);
            adapter = new LifeHackAdapter(context, listElements);
            ListView listview = (ListView) view.findViewById(R.id.listView);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }
    }

}
