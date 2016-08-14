package com.pythoncat.proxy.net.retrofit;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * <p>
 * 更新版本
 */
public class UpdateApi {


    private interface UpdateService {
        /**
         * 更新版本    <b style="color:red">Error Method!!!</b>
         *
         * @return duck
         */
//        @GET(NetPath.updateVersion)
//        Observable<Update> getVersionInfo();
    }

    /**
     * 更新版本 <b style="color:red">Error Method!!!</b>
     *
     * @return update
     */
//    @Deprecated
//    public static Observable<Update> updateApp() {
//
//        return new Retrofit.Builder()
//                .baseUrl(NetPath.baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build()
//                .create(UpdateService.class)
//                .getVersionInfo();
//    }
    /*
      public Observable<History> queryHistory() {
        //"history"
        //1.创建Retrofit对象

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
        //2.创建访问API的请求
        GankService gankService = retrofit.create(GankService.class);
        Observable<History> history = gankService.history("history");
        return history;
    }
     */


}
