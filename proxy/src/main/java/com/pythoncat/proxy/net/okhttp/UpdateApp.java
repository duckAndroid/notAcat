package com.pythoncat.proxy.net.okhttp;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.pythoncat.proxy.bean.Download;
import com.pythoncat.proxy.bean.Update;
import com.pythoncat.proxy.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * 更新版本
 */
public class UpdateApp {

    /**
     * 从文件中读取字符串信息
     *
     * @param url       fileUrl
     * @param localName 下载到本地保存的文件名 ｛路径：App.get().getFilesDir();｝
     * @param dirPath   保存的文件所在目录
     * @return destFile
     */
    public static Observable<File> downloadFile(String url, String dirPath, String localName) {

        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                // 创建okHttpClient对象
                // 创建一个Request
                // new call
                OkHttpManager.getHttpsClient()
                        .build()
                        .newCall(new Request.Builder()
                                .url(url)
                                .build())
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                byte[] bytes = response.body().bytes();
                                File localFile = new File(dirPath, localName);
                                FileOutputStream os = new FileOutputStream(localFile);
                                os.write(bytes);
                                os.flush();
                                os.close();
                                LogUtils.w("ssssssssss===" + localFile);
                                subscriber.onNext(localFile);
                                subscriber.onCompleted();
                            }
                        });
            }
        })
                //   onBackpressureBuffer() 方法将告诉Observable发射的数据如果比观察者消费的数据要更快的话，它必须把它们存储在缓存中并提供一个合适的时间给它们。
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io());
    }

    /**
     * file --> pojo <br>
     * 从 服务器 更新文件中获取 更新信息，并封装到pojo中
     *
     * @param url fileUrl
     * @return pojo
     */
    public static Observable<Update> getNewVersionPOJO(String url, String dirPath, String localName) {

        return downloadFile(url, dirPath, localName)
                .flatMap(file -> Observable.just(FileUtil.readFile(new File(dirPath, localName))))
                .flatMap(json -> Observable.just(new Gson().fromJson(json, Update.class)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param url       fileUrl
     * @param localName 下载到本地保存的文件名 ｛路径：App.get().getFilesDir();｝
     * @param dirPath   保存的文件所在目录
     * @return download info
     */
    public static Observable<Download> downloadFileWithProgress(String url, String dirPath, String localName) {
        String destPath = new File(dirPath, localName).getAbsolutePath();
        return Observable.create(new Observable.OnSubscribe<Download>() {
            int pro = 0;

            @Override
            public void call(Subscriber<? super Download> subscriber) {
                try {
                    new Progress().run(url, dirPath, localName, (bytesRead, contentLength, done) -> {
                        pro += bytesRead;
                        subscriber.onNext(new Download(bytesRead, contentLength, done, destPath));
                        if (done || bytesRead == contentLength) {
                            subscriber.onCompleted();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        })
                .onBackpressureBuffer()
//                .onBackpressureDrop() // todo 进度回调太多，消费不过来就丢弃一部分
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
