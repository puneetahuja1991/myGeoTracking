package com.abaqustest.mygeotrackingapp.network;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Rest Client
 *
 * @author Puneet Ahuja
 */
public class RestClient {
    private static final RestClient ourInstance = new RestClient();
    private ApiInterface apiInterface;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RestClient getInstance() {
        return ourInstance;
    }

    /**
     * Gets network api interface.
     *
     * @return the network api interface
     */
    public ApiInterface getNetworkApiInterface() {
        return apiInterface;
    }

    /**
     * Instantiates a new Hybris rest client.
     */
    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpoint.BASE_URL)
                .client(httpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    /**
     * Http client ok http client.
     *
     * @return the ok http client
     */
    private OkHttpClient httpClient(){
        return  new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .build();
    }


    /**
     * Url and header interceptor interceptor.
     *
     * @return the interceptor
     */
    private Interceptor urlAndHeaderInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("Content-Type","application/x-www-form-urlencoded");
            return chain.proceed(builder.build());
        };
    }


    /**
     * Http logging interceptor interceptor.
     *
     * @return the interceptor
     */
    private Interceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level;
        level = HttpLoggingInterceptor.Level.BODY;
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

}


