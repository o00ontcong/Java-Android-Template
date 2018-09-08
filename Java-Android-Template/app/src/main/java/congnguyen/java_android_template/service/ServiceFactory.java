package congnguyen.java_android_template.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import congnguyen.java_android_template.BuildConfig;
import congnguyen.java_android_template.util.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    ServiceFactory() {
        // Do nothing
    }

    private static volatile ServiceApi mServiceApi = null;

    public static ServiceApi getServiceInstance() {
        if (mServiceApi == null) {
            mServiceApi = ServiceFactory.create();
        }

        return mServiceApi;
    }

    private static ServiceApi create() {

        //SERVER IMT-soft
//        final String BASE_LC = "http://192.168.100.42:8083/Services/";

        //SERVER VIETTEL
        final  String BASE_LC = "http://171.255.192.109:8778/Services/";
        Gson gson = new GsonBuilder().serializeNulls().create();
        RxJava2CallAdapterFactory callAdapter = RxJava2CallAdapterFactory.create();

        OkHttpClient client = createHttpClient();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(BASE_LC);
        retrofitBuilder.client(client);

        retrofitBuilder.addConverterFactory( GsonConverterFactory.create(gson));
        retrofitBuilder.addCallAdapterFactory(callAdapter);

        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(ServiceApi.class);
    }

    private static OkHttpClient createHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            String token = Utils.getToken();
            Request.Builder builder = original.newBuilder();
            if (!TextUtils.isEmpty(token)) {
                builder.header("token", token);
            }

            builder.header("User-Agent", "Smas");
            builder.method(original.method(), original.body()).build();
            Request request = builder.build();

            return chain.proceed(request);
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
            interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptorBody);
        }

        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);
        httpClient.hostnameVerifier((hostname, session) -> true);

        return httpClient.build();
    }
}
