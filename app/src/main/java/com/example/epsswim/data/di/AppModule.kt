package com.example.epsswim.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.apollographql.apollo.ApolloClient
import com.example.epsswim.data.network.EpsClient
import com.example.epsswim.data.network.LoginApiInterface
import com.example.epsswim.data.repositories.tokenRepository.JWTManager
import com.example.epsswim.data.repositories.tokenRepository.JwtTokenDataStore
import com.example.epsswim.data.utils.Constants
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
object AppModule {
//    @Provides
//    @Singleton
//    fun provideApolloClient(): ApolloClient {
//        return ApolloClient.Builder()
//            .serverUrl("https://example.com/graphql")
//            .build()
//    }
//    @Provides
//    @Singleton
//    fun provideEpsClient(apolloClient: ApolloClient): EpsClient {
//        return EpsClient(apolloClient)
//    }
@Singleton
@Provides
fun provideLoginApi(): LoginApiInterface = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(LoginApiInterface::class.java)
}
