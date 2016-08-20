package com.pythoncat.proxy.net.okhttp;

import com.apkfuns.logutils.LogUtils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * <br/>
 * <b>add:忽略htts验证</b>
 *
 * @link http://blog.csdn.net/zhong1113/article/details/51492469
 */
public class OkHttpManager {

    /**
     * Trust every server - dont check for any certificate
     * <br/>
     * <b>add:忽略htts验证</b>
     */
    public static OkHttpClient.Builder getHttpsClient() {

        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
                LogUtils.d(authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                LogUtils.d(authType);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(),
                        Platform.get().trustManager(sslContext.getSocketFactory()))
                .hostnameVerifier(DO_NOT_VERIFY);
    }
}
