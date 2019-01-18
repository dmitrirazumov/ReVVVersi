package com.example.bigbo.revvversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityOptions extends AppCompatActivity {

    //переменные, отвечающие за выбранный фон
    public static boolean WALL1 = true;
    public static boolean WALL2 = false;
    public static boolean WALL3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //задание фона в соответствии с опциями
        if (WALL1) getWindow().setBackgroundDrawableResource(R.drawable.truepicture);
        if (WALL2) getWindow().setBackgroundDrawableResource(R.drawable.anothertruepicture);
        if (WALL3) getWindow().setBackgroundDrawableResource(R.drawable.withoutja);

        ImageView bcg1 = (ImageView) findViewById(R.id.wallpaper1);
        ImageView bcg2 = (ImageView) findViewById(R.id.wallpaper2);
        ImageView bcg3 = (ImageView) findViewById(R.id.wallpaper3);

        TextView back = (TextView) findViewById(R.id.backOptionsScreen);

        View.OnClickListener listenToOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.wallpaper1:
                        WALL1 = true;
                        WALL2 = false;
                        WALL3 = false;
                        getWindow().setBackgroundDrawableResource(R.drawable.truepicture);
                        break;
                    case R.id.wallpaper2:
                        WALL1 = false;
                        WALL2 = true;
                        WALL3 = false;
                        getWindow().setBackgroundDrawableResource(R.drawable.anothertruepicture);
                        break;
                    case R.id.wallpaper3:
                        WALL1 = false;
                        WALL2 = false;
                        WALL3 = true;
                        getWindow().setBackgroundDrawableResource(R.drawable.withoutja);
                        break;
                    case R.id.backOptionsScreen:
                        Intent gotoMenu = new Intent(ActivityOptions.this, ActivityMenu.class);
                        gotoMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(gotoMenu);
                        break;
                }
            }
        };

        bcg1.setOnClickListener(listenToOptions);
        bcg2.setOnClickListener(listenToOptions);
        bcg3.setOnClickListener(listenToOptions);
        back.setOnClickListener(listenToOptions);
    }
}
