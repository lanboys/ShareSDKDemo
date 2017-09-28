package com.bing.lan.share;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bing.lan.share.ShareSDKUtil.OneKeyShareUtil;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "我是标题";
                String url = "https://www.baidu.com/";
                String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501755647712&di=d49d1fa6d94f4ce701e1546d0aaad23c&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fszsbcar%2Fimages%2F001921ad0b15096e699c02.jpg";

                OneKeyShareUtil.showShare(Main2Activity.this, url, title, imageUrl);
            }
        });
    }
}
