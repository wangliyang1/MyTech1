package com.wd.tech.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.wd.tech.api.ApiService;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class NetUtil {

    private final ApiService api;

    private static final class Holder{
        private static final NetUtil NET_UTIL=new NetUtil();
    }

    public static NetUtil getInstance() {
        return Holder.NET_UTIL;
    }

    public NetUtil() {
        File file = new File(MyApp.mContext.getCacheDir().getAbsolutePath(), "http");
        Cache cache=new Cache(file,1024*1024*10);
        //拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttp
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        SharedPreferences sp = MyApp.mContext.getSharedPreferences("login.dp", MODE_PRIVATE);
                        if (sp.getBoolean("b",false)){
                           String sid = sp.getString("sid","");
                           int uid = sp.getInt("uid",-1);
                            Request request = chain.request().newBuilder()
                                    .addHeader("userId", uid+"")
                                    .addHeader("sessionId", sid)
                                    .build();
                            return  chain.proceed(request);
                        }else {
                            return chain.proceed(chain.request());
                        }

                    }
                })
                //添加缓存
                .addInterceptor(new CacheIntercept())
                .cache(cache)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttp)
                .baseUrl(MyUrls.BASE_OUTER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return api;
    }
    //get无参
    public void getNoParams(String url, final Class cls, final ICallback iCallback){
        api.getNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //get有参
    public void getDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.getDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //get 头参
    public void doGetHeaderParams(String url, Class cls, Map<String,Object> map, ICallback netCallbacK){
        api.doGetHeaderParams(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            if (o != null){
                                netCallbacK.onSuccess(o);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        netCallbacK.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post头像
    public void postDoHeadPic(String url, final Class cls, MultipartBody.Part image, final ICallback iCallback){
        api.postDoHeadPic(url,image).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post无参
    public void postNoParams(String url, final Class cls, final ICallback iCallback){
        api.postNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post有参
    public void postDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.postDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //put 无参
    public void putNoParams(String url,final Class cls,final ICallback iCallback){
        api.putNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    };
    //put 有参
    public void putDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.putDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //dlt无参
    public void dltNoParams(String url, final Class cls, final ICallback iCallback){
        api.dltNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //dlt有参
    public void dltDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.dltDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //接口回调
    public interface ICallback<T>{
        void onSuccess(T t);
        void onError(Throwable e);
    }
    //判断是否有网
    public static boolean hasNet(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }else {
            return false;
        }
    }
}
