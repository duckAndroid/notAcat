/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pythoncat.proxy.net.okhttp;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.proxy.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * from: OKHTTP 官方sample 中的源码
 */
public final class Progress {

    /**
     * 使用okhttp 进行文件下载
     *
     * @param url              file url
     * @param dir              下载后保存的目录
     * @param name             下载后保存的文件名
     * @param progressListener 进度回调
     * @throws IOException
     */
    public void run(String url, String dir, String name, ProgressListener progressListener) throws IOException {
        FileOutputStream out = null;
        try {
            Response response = OkHttpManager.getHttpsClient()
                    .addNetworkInterceptor(chain -> {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();
                    })
                    .build().newCall(new Request.Builder()
                            .url(url)
                            .build()).execute();
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);

//            System.out.println(response.body().string());
            byte[] bytes = response.body().bytes(); // 拿到的是完整的文件，不是文件的一部分哦！
            LogUtils.e("len====" + bytes.length);
            out = new FileOutputStream(new File(dir, name));
            out.write(bytes);
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    @Deprecated
    public static void main(String... args) throws Exception {
        new Progress().run("https://publicobject.com/helloworld.txt", App.get().getCacheDir().getPath(), "aa.apk", null);
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            };
        }
    }

    interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }
}