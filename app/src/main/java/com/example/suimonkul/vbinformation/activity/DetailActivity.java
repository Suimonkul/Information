package com.example.suimonkul.vbinformation.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suimonkul.vbinformation.R;
import com.example.suimonkul.vbinformation.ddt.ItAsyncTask;
import com.example.suimonkul.vbinformation.ddt.CookingAsyncTask;

public class DetailActivity extends AppCompatActivity {

    TextView tvMore;
    TextView tvTitle;
    ImageView im1, im2, im3, im4, im5;

    private static final int STYLE = R.style.AppDefault;
    private static final int LAYOUT = R.layout.activity_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setTheme(STYLE);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        tvMore = (TextView) findViewById(R.id.tvMore);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        im1 = (ImageView) findViewById(R.id.imageFirst);

        Intent intent = getIntent();
        Boolean peopleNews = intent.getBooleanExtra("peopleNews", false);

        if (peopleNews) {
            CookingAsyncTask task1 = new CookingAsyncTask(this, tvMore, tvTitle, im1);
            task1.execute();
        } else {
            ItAsyncTask task = new ItAsyncTask(this, tvMore, tvTitle, im1);
            task.execute();

        }
    }
}
