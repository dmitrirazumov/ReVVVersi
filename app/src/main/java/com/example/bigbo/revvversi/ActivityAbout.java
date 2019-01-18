package com.example.bigbo.revvversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //задание фона в соответствии с опциями
        if (ActivityOptions.WALL1)
            getWindow().setBackgroundDrawableResource(R.drawable.truepicture);
        if (ActivityOptions.WALL2)
            getWindow().setBackgroundDrawableResource(R.drawable.anothertruepicture);
        if (ActivityOptions.WALL3) getWindow().setBackgroundDrawableResource(R.drawable.withoutja);

        TextView back = (TextView) findViewById(R.id.backAboutScreen);

        View.OnClickListener plsListenBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMenu = new Intent(ActivityAbout.this, ActivityMenu.class);
                gotoMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(gotoMenu);
            }
        };

        back.setOnClickListener(plsListenBack);
    }
}
