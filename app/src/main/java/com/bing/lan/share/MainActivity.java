package com.bing.lan.share;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "-->shareSDK";
    private Toolbar mToolbar;
    private Button mButton;

    //  增加  显眼的提示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mButton = (Button) findViewById(R.id.btn_open);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();

                showShare();
                //showDialog();
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

        View inflate = View.inflate(this, R.layout.view, null);

        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("我是弹窗");
        dialog.setView(inflate, 0, 0, 0, 0);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 非常重要：设置对话框弹出的位置
        window.setContentView(R.layout.view);//无效

        // window.setAttributes(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();
    }

    private void showShare() {

        //http://bbs.mob.com/thread-22130-1-1.html
        /**
         * 第七步：配置AppKey和AppSecret有两种方式ShareSDK配置appkey和appsecret的配置，前面配置Manifest文件的时候已经有说明在application中配置节点等；
         配置AppKey和AppSecret有两种方式：
         （1）通过AndroidManifest配置
         （2）通过代码配置
         以上方法择一即可，建议使用第一种方式进行配置。
         第一种：通过AndroidManifest配置：
         （1）在Application节点下添加以下属性：
         android:name="com.mob.MobApplication"
         注意：如果你有自己的Application类，那么也可以让你的Application类继承MobApplication即可。
         （2）在Application节点下添加以下子节点：
         //<!-- 通过AndroidManifest配置AppKey和AppSecret，如果你选择通过代码配置，则不需要配置以下meta-data --><meta-data android:name="Mob-AppKey" android:value="你的AppKey"/><meta-data android:name="Mob-AppSecret" android:value="你的AppSecret"/>
         第二种：通过代码配置：
         如果选择通过代码配置，则不需要继承MobApplication，只要在使用ShareSDK之前，调用以下代码：
         // 通过代码注册你的AppKey和AppSecretMobSDK.init(context, "你的AppKey", "你的AppSecret");
         */

        //        <meta-data android:name="Mob-AppKey" android:value="1f4925d768e6e"/>
        //<meta-data android:name="Mob-AppSecret" android:value="a8340efcfa6c560379fbfe7ba7ca2583"/>

        MobSDK.init(this, "1f4925d768e6e", "a8340efcfa6c560379fbfe7ba7ca2583");

        String url = "http://www.mob.com/downloadDetail/ShareSDK/android";
        String url2 = "http://news.3news.cn/html/jin/2017/0710/64380.html";
        String url3 = "https://www.baidu.com/";

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 三种方法 : 1 xml中更改   2.删除jar包   3. 方法如下
        oks.addHiddenPlatform(QZone.NAME);
        oks.addHiddenPlatform(WechatFavorite.NAME);
        oks.addHiddenPlatform(Wechat.NAME);
        // oks.addHiddenPlatform(SinaWeibo.NAME);
        //oks.addHiddenPlatform(ShortMessage.NAME);
        //oks.addHiddenPlatform(AlipayMoments.NAME);
        //oks.addHiddenPlatform(Alipay.NAME);

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("我是分享标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url3);

        // text是分享文本，所有平台都需要这个字段
        //oks.setText("我是分享文本我是分享文本我是分享文本我是分享文本我是分享文本");

        //没有图片就会 带上分享文本 (二选一？？)
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501755647712&di=d49d1fa6d94f4ce701e1546d0aaad23c&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fszsbcar%2Fimages%2F001921ad0b15096e699c02.jpg");
        //图片地址不能是内网地址

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

        //515b665aa60b24e21caf5d152b60e71a

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
