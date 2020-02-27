package pl.shoppinglistexample.persistence.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity
import javax.inject.Qualifier

@Dao
abstract class ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertList(shoppingList: ShoppingListEntity): Completable

    @Query("UPDATE shopping_list SET is_archived = :isArchived WHERE id = :id")
    abstract fun setIsArchived(id: Long, isArchived: Boolean): Completable

    @Query("SELECT * FROM shopping_list WHERE is_archived = 0 ORDER BY timestamp_created DESC")
    abstract fun observeCurrentLists(): Flowable<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_list WHERE is_archived = 1 ORDER BY timestamp_created DESC")
    abstract fun observeArchivedLists(): Flowable<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    abstract fun observeListWithId(id: Long): Flowable<ShoppingListEntity>

}
