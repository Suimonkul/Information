package com.example.suimonkul.vsesamoe.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.suimonkul.vsesamoe.R;
import com.example.suimonkul.vsesamoe.adapter.SportAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SportFragment extends AbstractTabsFragment {


    private static final int LAYOUT = R.layout.fragment_last_news;

    public static SportFragment getInstance(Context context) {
        Bundle args = new Bundle();
        SportFragment fragment = new SportFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.sport_news));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);


        DataDownloadLast task = new DataDownloadLast();
        task.execute();
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class DataDownloadLast extends AsyncTask<String, String, Elements> {

        SportAdapter adapter;
        Elements listElements = null;


        @Override
        protected Elements doInBackground(String... params) {

            Document document;
            listElements = new Elements();
            String urlSite = "http://korrespondent.net/tag/36431/";
            try {

                document = Jsoup.connect(urlSite).get();
                listElements = document.select("div.articles-list");
                Log.d("SportLog", "Progress...  " + listElements);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return listElements;
        }

        @Override
        protected void onPostExecute(Elements listElements) {
            super.onPostExecute(listElements);
            if (listElements.size() == 0){
                Log.d("SportLog", "Result = Null  =  " + listElements);
                System.out.println("Null");
            }else {
                Log.d("SportLog", "Result...  =  " + listElements);
                System.out.println("Null эмес");
            }

            adapter = new SportAdapter(context, listElements);
            ListView listview = (ListView) view.findViewById(R.id.listView);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }
}
