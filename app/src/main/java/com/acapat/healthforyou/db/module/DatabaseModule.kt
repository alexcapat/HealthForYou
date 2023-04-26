package com.acapat.healthforyou.db.module

import android.content.Context
import androidx.room.Room
import com.acapat.healthforyou.db.HealthForYouDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, HealthForYouDatabase::class.java, "health_for_you").build()

  @Provides fun provideFoodDao(database: HealthForYouDatabase) = database.foodDao()
  @Provides fun provideBMIDao(database: HealthForYouDatabase) = database.bodyDao()
  @Provides fun provideWaterDao(database: HealthForYouDatabase) = database.waterDao()
  @Provides fun provideSleepDao(database: HealthForYouDatabase) = database.sleepDao()


}
