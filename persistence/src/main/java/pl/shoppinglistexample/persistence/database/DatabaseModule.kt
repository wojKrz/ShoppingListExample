package pl.shoppinglistexample.persistence.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {

    @[Provides Singleton]
    internal fun provideShoppingListsDao(database: ShoppingListsDatabase): ShoppingListDao =
        database.shoppingListDao()

    @[Provides Singleton]
    internal fun provideDatabase(@ApplicationContext context: Context): ShoppingListsDatabase =
        Room.databaseBuilder(context, ShoppingListsDatabase::class.java, Config.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    internal object Config {
        internal const val DB_NAME = "shoppinglists.db"
    }
}
