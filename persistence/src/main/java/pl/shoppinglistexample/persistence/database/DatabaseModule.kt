package pl.shoppinglistexample.persistence.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): ShoppingListsDatabase =
        Room.databaseBuilder(context, ShoppingListsDatabase::class.java, Config.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()


    internal object Config {
        internal const val DB_NAME = "shoppinglists.db"
    }
}
