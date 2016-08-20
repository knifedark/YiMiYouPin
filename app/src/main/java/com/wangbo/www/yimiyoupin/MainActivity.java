package com.wangbo.www.yimiyoupin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wangbo.www.yimiyoupin.activity.MagazineDetailsA;

public class MainActivity extends AppCompatActivity {
    private Handler mhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.button_activitymagazine:
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,MagazineDetailsA.class);
                        intent.putExtra("id",60);
                        startActivity(intent);

                    }
                },2000);
                break;
        }
    }
}
