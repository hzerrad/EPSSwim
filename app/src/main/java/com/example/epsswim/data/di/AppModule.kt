package com.example.epsswim.data.di

import com.apollographql.apollo.ApolloClient
import com.example.epsswim.data.network.EpsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://example.com/graphql")
            .build()
    }
    @Provides
    @Singleton
    fun provideEpsClient(apolloClient: ApolloClient): EpsClient {
        return EpsClient(apolloClient)
    }
}
