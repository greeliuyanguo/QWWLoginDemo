package com.kankan.qwwlogindemo.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.rxbinding.view.RxView;
import com.kankan.qwwlogindemo.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    //APP_ID替换为该应用从官方网站申请到的合法appId
    private static final String APP_ID = "";
    //IWXAPI是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_login);

        initView();
        initWechatLogin();
        initWechatLogout();
    }

    private void initView() {

    }

    private void initWechatLogin() {
        RxView.clicks(findViewById(R.id.wechatLogin))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doWechatLogin();
                    }
                });
    }

    private void doWechatLogin() {
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        //将应用的appId注册到微信
        api.registerApp(APP_ID);

        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "none";
        api.sendReq(req);
        finish();
    }

    private void initWechatLogout() {
        RxView.clicks(findViewById(R.id.wechatLogout))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        doWechatLogout();
                    }
                });
    }

    private void doWechatLogout() {

    }

    /**
     * 微信发送的请求将回调到onReq方法
     *
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 发送到微信请求的响应结果将回调到onResp方法
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {

    }
}
