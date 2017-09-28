package com.bing.lan.share.ShareSDKUtil;

import android.app.Activity;

import com.bing.lan.comm.app.AppUtil;
import com.bing.lan.comm.mvp.activity.BaseActivity;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.share.ShareSDKUtil.bean.QQBean;
import com.bing.lan.share.ShareSDKUtil.bean.WechatBean;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by 蓝兵 on 2017/8/3.
 */

public class OneKeyShareUtil {

    protected static final LogUtil log = LogUtil.getLogUtil(OneKeyShareUtil.class, LogUtil.LOG_VERBOSE);

    static {
        //MobSDK.init(AppUtil.getAppContext(), "1f99ecd05a0b8", "b57beac4acfffb098b2dde5934830bdd");
        //MobSDK.init(AppUtil.getAppContext(), "21258b5828bb0", "964c458abf4d594de6c4157e7eddfe3f");
        MobSDK.init(AppUtil.getAppContext(), "1f4925d768e6e", "a8340efcfa6c560379fbfe7ba7ca2583");

    }

    public static void PlatformUnbinding(String platformName) {
        Platform platform = ShareSDK.getPlatform(platformName);
        platform.removeAccount(true);
    }

    public static void PlatformBinding(final BaseActivity context, String platformName, final BindingListener bindingListener) {

        Platform platform = ShareSDK.getPlatform(platformName);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        //context.showProgressDialog("正在绑定..");
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(final Platform platform, int arg1, Throwable arg2) {
                arg2.printStackTrace();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.showError("绑定失败");
                        //context.dismissProgressDialog();
                        if (bindingListener != null) {
                            bindingListener.onBindingFail(platform);
                        }
                    }
                });
            }

            @Override
            public void onComplete(final Platform platform, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                //context.dismissProgressDialog();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String msg = platform.getDb().exportData();
                            log.d("run(): " + msg);
                            Object obj = null;
                            if (platform instanceof QQ) {
                                obj = QQBean.objectFromData(msg);
                            } else if (platform instanceof Wechat) {
                                obj = WechatBean.objectFromData(msg);
                            } else {
                                throw new RuntimeException("平台不对");
                            }

                            log.d("onComplete(): " + obj);
                            if (bindingListener != null) {
                                bindingListener.onBindingSuccess(platform, obj);
                            }
                            context.showToast("绑定成功");
                        } catch (Exception e) {
                            context.showError("绑定失败");
                            log.e("onCompleteee():  " + e.getLocalizedMessage());
                            if (bindingListener != null) {
                                bindingListener.onBindingFail(platform);
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancel(final Platform platform, int arg1) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.showError("取消绑定");
                        //context.dismissProgressDialog();
                        if (bindingListener != null) {
                            bindingListener.onBindingFail(platform);
                        }
                    }
                });
            }
        });

        //authorize与showUser单独调用一个即可
        //weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }

    /**
     * @param context
     * @param url      分享的链接
     * @param title    标题
     * @param imageUrl 图片链接
     */
    public static void showShare(final Activity context, String url, String title, String imageUrl) {

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
         // 通过AndroidManifest配置AppKey和AppSecret，如果你选择通过代码配置，则不需要配置以下meta-data
         <meta-data android:name="Mob-AppKey" android:value="你的AppKey"/><meta-data android:name="Mob-AppSecret" android:value="你的AppSecret"/>
         第二种：通过代码配置：
         如果选择通过代码配置，则不需要继承MobApplication，只要在使用ShareSDK之前，调用以下代码：
         // 通过代码注册你的AppKey和AppSecretMobSDK.init(context, "你的AppKey", "你的AppSecret");
         */

        //        <meta-data android:name="Mob-AppKey" android:value="1f4925d768e6e"/>
        //<meta-data android:name="Mob-AppSecret" android:value="a8340efcfa6c560379fbfe7ba7ca2583"/>

        //String url = "http://www.mob.com/downloadDetail/ShareSDK/android";
        //String url2 = "http://news.3news.cn/html/jin/2017/0710/64380.html";
        //String url3 = "https://www.baidu.com/";

        //if (MyBuildType.BUILD_TYPE_DEBUG == BuildConfig.BUILD_TYPES) {
        //    url = "https://www.baidu.com/";
        //    imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501755647712&di=d49d1fa6d94f4ce701e1546d0aaad23c&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fszsbcar%2Fimages%2F001921ad0b15096e699c02.jpg";
        //}

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 三种方法 : 1 xml中更改   2.删除jar包   3. 方法如下
        oks.addHiddenPlatform(QZone.NAME);
        oks.addHiddenPlatform(WechatFavorite.NAME);
        //oks.addHiddenPlatform(Wechat.NAME);
        // oks.addHiddenPlatform(SinaWeibo.NAME);
        //oks.addHiddenPlatform(ShortMessage.NAME);
        //oks.addHiddenPlatform(AlipayMoments.NAME);
        //oks.addHiddenPlatform(Alipay.NAME);

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);

        // text是分享文本，所有平台都需要这个字段
        oks.setText("");

        //没有图片就会 带上分享文本 (二选一？？)

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");//确保SDcard下面存在此张图片

        oks.setImageUrl(imageUrl);

        // url仅在微信（包括好友和朋友圈），新浪微博  中使用
        // 新浪微博  必须要是安全域名的子域名，不能短时间内发送同一条微博
        // 微信好友 微信收藏 朋友圈 中 必须 带上图片才能点开，但是新浪微博不需要
        oks.setUrl(url);

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("");

        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("");

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // oks.setPlatform(Wechat.NAME);
        // oks.setPlatform(QQ.NAME);
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //Log.e(TAG, platform.getName());
                log.i("onComplete():shareSdk 分享成功");
                //Log.e(TAG, hashMap.toString());

                //context.showToast("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //Log.e(TAG, platform.getName());
                log.e("onError():shareSdk 分享成功  " + throwable.getLocalizedMessage());
                //Log.e(TAG, throwable.getLocalizedMessage());
                //context.showError("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //Log.e(TAG, platform.getName());
                //Log.e(TAG, "取消");
                //context.showToast("取消分享");
                log.e("onError():shareSdk 取消分享");
            }
        });

        oks.setSilent(true);

        // 启动分享GUI
        oks.show(context);
    }
}
