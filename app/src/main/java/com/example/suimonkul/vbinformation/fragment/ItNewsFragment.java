package com.example.suimonkul.vbinformation.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.suimonkul.vbinformation.R;
import com.example.suimonkul.vbinformation.adapter.ItNewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ItNewsFragment extends AbstractTabsFragment {

    private static final int LAYOUT = R.layout.fragment_last_news;

    public static ItNewsFragment getInstance(Context context) {
        Bundle args = new Bundle();
        ItNewsFragment fragment = new ItNewsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.last_news));

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

        ItNewsAdapter adapter;
        Elements listElements;


        @Override
        protected Elements doInBackground(String... params) {

            Document document;
            listElements = new Elements();
            String urlSite = "http://www.4pda.ru/";
            try {

                document = Jsoup.connect(urlSite).get();
                listElements = document.select("article.post");


            } catch (IOException e) {
                e.printStackTrace();
            }

            return listElements;
        }

        @Override
        protected void onPostExecute(Elements listElements) {
            super.onPostExecute(listElements);
            adapter = new ItNewsAdapter(context, listElements);
            ListView listview = (ListView) view.findViewById(R.id.listView);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }
}
