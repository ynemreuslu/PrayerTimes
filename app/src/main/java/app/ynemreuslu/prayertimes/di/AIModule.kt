package app.ynemreuslu.prayertimes.di

import android.content.Context
import app.ynemreuslu.prayertimes.BuildConfig
import app.ynemreuslu.prayertimes.common.Constants
import app.ynemreuslu.prayertimes.common.Constants.MAX_OUTPUT_TOKEN
import app.ynemreuslu.prayertimes.common.Constants.MODEL_NAME
import app.ynemreuslu.prayertimes.common.Constants.TOP_K
import app.ynemreuslu.prayertimes.common.Constants.TOP_P
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AIModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        return GenerativeModel(
            modelName = MODEL_NAME,
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = generationConfig {
                temperature = Constants.TEMPERATURE
                topK = TOP_K
                topP = TOP_P
                maxOutputTokens = MAX_OUTPUT_TOKEN
            })
    }

}

