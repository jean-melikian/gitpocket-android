package fr.ozoneprojects.gitpocket.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import fr.ozoneprojects.gitpocket.GITHUB_API_BASE_URL
import fr.ozoneprojects.gitpocket.network.UsersApi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUserApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()

//        logging.level = HttpLoggingInterceptor.Level.BODY
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        clientBuilder.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(GITHUB_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(clientBuilder.build())
            .build()
    }
}