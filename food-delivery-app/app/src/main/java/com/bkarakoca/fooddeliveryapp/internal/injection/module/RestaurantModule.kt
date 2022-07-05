package com.bkarakoca.fooddeliveryapp.internal.injection.module

import android.content.Context
import androidx.room.Room
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepositoryImpl
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.RestaurantDAO
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.RestaurantDataBase
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.RestaurantDataSource
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RestaurantModule {

    @Provides
    @Singleton
    fun provideRestaurantRepository(
        restaurantDataSource: RestaurantDataSource,
        restaurantListMapper: RestaurantListMapper
    ): RestaurantRepository =
        RestaurantRepositoryImpl(restaurantDataSource, restaurantListMapper)

    @Provides
    fun provideRestaurantDatabase(@ApplicationContext context : Context) =
        Room.databaseBuilder(context, RestaurantDataBase::class.java, "favoriteRestaurant")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRestaurantDAO(appDatabase: RestaurantDataBase): RestaurantDAO {
        return appDatabase.restaurantDAO()
    }

}