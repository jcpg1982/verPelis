package pe.com.master.machines.network.di

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pe.com.master.machines.design.utils.Utils.isInternetReachable
import pe.com.master.machines.network.api.ApiService
import pe.com.master.machines.network.utils.AuthInterceptor
import pe.com.master.machines.network.utils.Utils.BASE_URL
import pe.com.master.machines.network.utils.Utils.TMDB_API_KEY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val TAG = NetworkModule::class.java.simpleName

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message: String ->
            Log.d(TAG, message)
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheSize = 50 * 1024 * 1024L
        return Cache(File(context.cacheDir, "http_cache"), cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor(TMDB_API_KEY))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isInternetReachable) {
                    request.newBuilder()
                        .header("Cache-Control", "public, max-age=60")
                        .build()
                } else {
                    request.newBuilder()
                        .header("Cache-Control", "public, max-age=60, max-stale=14400")
                        .build()
                }
                chain.proceed(request)
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    @Singleton
    @Synchronized
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideIApiClient(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}