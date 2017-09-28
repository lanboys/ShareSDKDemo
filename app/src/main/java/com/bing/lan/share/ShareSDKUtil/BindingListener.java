package com.bing.lan.share.ShareSDKUtil;

import cn.sharesdk.framework.Platform;

/**
 * Created by 蓝兵 on 2017/9/26.
 */

public interface BindingListener {

    void onBindingSuccess(Platform platform, Object obj);

    void onBindingFail(Platform platform);
}