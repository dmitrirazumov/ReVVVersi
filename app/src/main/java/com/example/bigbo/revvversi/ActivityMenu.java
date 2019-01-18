package com.example.bigbo.revvversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityMenu extends AppCompatActivity {

    public static boolean forInstrumentalTest = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //задание фона в соответствии с опциями
        if (ActivityOptions.WALL1)
            getWindow().setBackgroundDrawableResource(R.drawable.truepicture);
        if (ActivityOptions.WALL2)
            getWindow().setBackgroundDrawableResource(R.drawable.anothertruepicture);
        if (ActivityOptions.WALL3) getWindow().setBackgroundDrawableResource(R.drawable.withoutja);

        ImageView start = (ImageView) findViewById(R.id.startFirstScreen);
        ImageView options = (ImageView) findViewById(R.id.optionsFirstScreen);
        ImageView about = (ImageView) findViewById(R.id.aboutFirstScreen);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.startFirstScreen:
                        Intent gotoGame = new Intent(ActivityMenu.this, ActivityGame.class);
                        startActivity(gotoGame);
                        break;
                    case R.id.optionsFirstScreen:
                        Intent gotoOptions = new Intent(ActivityMenu.this, ActivityOptions.class);
                        forInstrumentalTesting(gotoOptions);
                        startActivity(gotoOptions);
                        break;
                    case R.id.aboutFirstScreen:
                        Intent gotoAbout = new Intent(ActivityMenu.this, ActivityAbout.class);
                        startActivity(gotoAbout);
                        break;
                }
            }
        };

        start.setOnClickListener(onClickListener);
        options.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
    }

    //необходимо для корректной работы инструментальных тестов
    private void forInstrumentalTesting(Intent button) {
        if (forInstrumentalTest) {
            button.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            button.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
    }
}
