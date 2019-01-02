package skilllotto.com.finvest.network

import java.util.concurrent.TimeUnit

//import durgesh.happycab.taxibooking.util.ToStringConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import skilllotto.com.finvest.Utils.ToStringConverterFactory

//import retrofit2.converter.scalars.ScalarsConverterFactory


object ApiClient {

    val BASE_URL = "put your Url" 
	

    private var retrofit: Retrofit? = null
    private var retrofit1: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
            }
            return this!!.retrofit!!
        }

    fun getClient(url: String): Retrofit {
        // if(retrofit1==null) {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        retrofit1 = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        //}
        return this!!.retrofit1!!
    }


    fun getClient1(url: String): Retrofit {
        // if(retrofit1==null) {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        retrofit1 = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(ToStringConverterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        //}
        return this!!.retrofit1!!
    }

}
