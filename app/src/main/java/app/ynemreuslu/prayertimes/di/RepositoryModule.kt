package app.ynemreuslu.prayertimes.di

import app.ynemreuslu.prayertimes.data.repository.PrayerTimeRepositoryImpl
import app.ynemreuslu.prayertimes.domain.repository.PrayerTimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPrayerTimeRepository(
        prayerTimeRepositoryImpl: PrayerTimeRepositoryImpl
    ): PrayerTimeRepository

}