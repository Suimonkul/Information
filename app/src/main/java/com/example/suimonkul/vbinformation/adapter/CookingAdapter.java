package com.example.suimonkul.vbinformation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suimonkul.vbinformation.R;
import com.example.suimonkul.vbinformation.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Suimonkul on 16.06.2016.
 */

public class CookingAdapter extends BaseAdapter {
    Elements list;
    Context context = new FragmentActivity();
    LayoutInflater inflater;

    public CookingAdapter(Context context, Elements list) {
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
        final HolderPeoples holderPeoples;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item, null, true);
            holderPeoples = new HolderPeoples();
            holderPeoples.cardViewPeople = (CardView) view.findViewById(R.id.cardView);
            holderPeoples.textViewPeople = (TextView) view.findViewById(R.id.tvInfo);
            holderPeoples.textViewPeopleDs = (TextView) view.findViewById(R.id.tvInfoDescription);
            holderPeoples.imageView = (ImageView) view.findViewById(R.id.titleImage);
            view.setTag(holderPeoples);
        } else {
            holderPeoples = (HolderPeoples) view.getTag();
        }
        final Element element = list.get(position);
        Log.d("myLogPeople", "" + element);
        holderPeoples.textViewPeople.setText(element.select("a.articlesbaseresultheader").text());
        holderPeoples.textViewPeople.setText(element.select("div[style = margin-top:5px;padding-right:4px;]").text());
        Picasso.with(context).load(element.select("img").attr("src")).into(holderPeoples.imageView);
        holderPeoples.cardViewPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Intent request = new Intent(context, DetailActivity.class);
                request.putExtra("peopleNews", true);
                intent.putExtra("url", element.select("a").attr("href"));
                context.startActivity(intent);
            }
        });
        return view;
    }

    public class HolderPeoples {
        TextView textViewPeople;
        TextView textViewPeopleDs;
        CardView cardViewPeople;
        ImageView imageView;
    }
}
