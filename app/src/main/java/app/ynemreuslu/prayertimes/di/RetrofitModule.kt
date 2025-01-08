package app.ynemreuslu.prayertimes.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import app.ynemreuslu.prayertimes.common.Constants.BASE_URL
import app.ynemreuslu.prayertimes.data.source.PrayerTimeApi
import app.ynemreuslu.prayertimes.data.source.QibleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePrayerTimeApi(retrofit: Retrofit): PrayerTimeApi {
        return retrofit.create(PrayerTimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQibleApi(retrofit: Retrofit): QibleApi {
        return retrofit.create(QibleApi::class.java)
    }
}