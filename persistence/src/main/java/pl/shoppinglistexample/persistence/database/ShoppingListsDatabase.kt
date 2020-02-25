package pl.shoppinglistexample.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity

@Database(
    version = 1,
    entities = [
        ShoppingListEntity::class
    ]
)
abstract class ShoppingListsDatabase : RoomDatabase() {

    abstract fun shoppingListDao() : ShoppingListDao

}
