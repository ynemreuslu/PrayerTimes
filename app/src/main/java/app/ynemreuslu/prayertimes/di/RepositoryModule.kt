package app.ynemreuslu.prayertimes.di

import app.ynemreuslu.prayertimes.data.repository.PrayerTimeRepositoryImpl
import app.ynemreuslu.prayertimes.domain.repository.PrayerTimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPrayerTimeRepository(
        prayerTimeRepositoryImpl: PrayerTimeRepositoryImpl
    ): PrayerTimeRepository

}