package pl.shoppinglistexample.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list")
open class ShoppingListEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0L

)
