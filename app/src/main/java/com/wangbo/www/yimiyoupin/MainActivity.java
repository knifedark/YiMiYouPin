package com.wangbo.www.yimiyoupin;

import android.content.Intent;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wangbo.www.yimiyoupin.activity.MagazineDetailsA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.button_activitymagazine)
    Button buttonActivitymagazine;
    private Handler mhandler = new Handler();
    private Unbinder bind = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=ButterKnife.bind(this);
        buttonActivitymagazine.setOnClickListener(this);

    }

     @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_activitymagazine:
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, MagazineDetailsA.class);
                        intent.putExtra("id", 60+"");
                        startActivity(intent);

                    }
                }, 2000);
                break;
        }

    }
}
