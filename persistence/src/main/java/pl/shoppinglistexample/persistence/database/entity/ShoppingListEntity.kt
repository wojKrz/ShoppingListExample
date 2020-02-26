package pl.shoppinglistexample.persistence.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list")
open class ShoppingListEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long?  = null,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "timestamp_created")
    var timestampCreated: Long,

    @ColumnInfo(name = "items")
    var items: List<String>? = emptyList(),

    @ColumnInfo(name = "is_archived")
    var isArchived: Boolean

)
