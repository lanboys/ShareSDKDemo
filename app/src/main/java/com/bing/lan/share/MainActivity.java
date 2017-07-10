package com.bing.lan.share;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "-->shareSDK";
    private Toolbar mToolbar;

    //  增加  显眼的提示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();

                showShare();
                // showDialog();
            }
        });

        // Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        // weibo.setPlatformActionListener(new PlatformActionListener() {
        //     @Override
        //     public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        //
        //     }
        //
        //     @Override
        //     public void onError(Platform platform, int i, Throwable throwable) {
        //
        //     }
        //
        //     @Override
        //     public void onCancel(Platform platform, int i) {
        //
        //     }
        // }); // 设置分享事件回调

    }

    private void showDialog() {



        // View view = LayoutInflater.from(this).inflate(R.layout.view, null);
        // //背景颜色
        // view.setBackgroundColor(Color.WHITE);
        // PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // //显示（自定义位置）
        // popupWindow.showAtLocation(new TextView(this), Gravity.NO_GRAVITY  /* | Gravity.CENTER_VERTICAL*/, 100, 300);
        // // popupWindow .showAsDropDown(mToolbar,0,0);//显示在控件下面
        //关闭
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("我是弹窗");
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 非常重要：设置对话框弹出的位置
        window.setContentView(R.layout.view);

        // window.setAttributes(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();
    }

    private void showShare() {
        String url = "http://www.mob.com/downloadDetail/ShareSDK/android";
        String url2 = "http://news.3news.cn/html/jin/2017/0710/64380.html";
        String url3 = "https://www.baidu.com/";

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 三种方法 : 1 xml中更改   2.删除jar包   3. 方法如下
        // oks.addHiddenPlatform(SinaWeibo.NAME);
        // oks.addHiddenPlatform(QZone.NAME);
        // oks.addHiddenPlatform(WechatFavorite.NAME);
        // oks.addHiddenPlatform(ShortMessage.NAME);
        // oks.addHiddenPlatform(AlipayMoments.NAME);
        // oks.addHiddenPlatform(Alipay.NAME);

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("我是分享标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url3);

        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本我是分享文本我是分享文本我是分享文本我是分享文本");

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");//确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈），新浪微博  中使用
        // 新浪微博  必须要是安全域名的子域名，不能短时间内发送同一条微博
        // 微信好友 微信收藏 朋友圈 中 必须 带上图片才能点开，但是新浪微博不需要
        oks.setUrl(url);

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");

        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("此内容的网站名称");

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url2);

        // oks.setPlatform(Wechat.NAME);
        // oks.setPlatform(QQ.NAME);
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e(TAG, platform.getName());
                Log.e(TAG, "成功");
                Log.e(TAG, hashMap.toString());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e(TAG, platform.getName());
                Log.e(TAG, "失败");
                Log.e(TAG, throwable.getLocalizedMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e(TAG, platform.getName());
                Log.e(TAG, "取消");
            }
        });

        oks.setSilent(true);

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
