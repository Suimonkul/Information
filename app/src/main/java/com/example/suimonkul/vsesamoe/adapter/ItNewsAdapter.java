package com.example.suimonkul.vsesamoe.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.suimonkul.vsesamoe.R;
import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Suimonkul on 13.06.2016.
 */

public class ItNewsAdapter extends BaseAdapter {
    Elements list;
    Context context = new FragmentActivity();
    LayoutInflater inflater;

    public ItNewsAdapter(Context context, Elements list) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Element getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HolderLast holderLast;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item, null, true);
            holderLast = new HolderLast();
            holderLast.textViewDescription = (TextView) view.findViewById(R.id.tvInfoDescription);
            holderLast.textView = (TextView) view.findViewById(R.id.tvInfo);
            holderLast.cardView = (CardView) view.findViewById(R.id.cardView);
            holderLast.imageView = (ImageView) view.findViewById(R.id.titleImage);
            view.setTag(holderLast);
        } else {
            holderLast = (HolderLast) view.getTag();
        }

        final Element element = list.get(position);
//        Log.d("myLogImg", "abc"+element);
        holderLast.textView.setText(element.select("h1 span").text());
        holderLast.textViewDescription.setText(element.select("div[itemprop = description]").text());
        Picasso.with(context).load(element.select("img").attr("src")).into(holderLast.imageView);

        Log.d("URL", element.select("a").attr("href"));
        return view;
    }

    public class HolderLast {
        TextView textView;
        TextView textViewDescription;
        ImageView imageView;
        CardView cardView;
    }
}
