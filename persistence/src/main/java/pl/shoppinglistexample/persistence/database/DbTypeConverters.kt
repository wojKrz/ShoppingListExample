package pl.shoppinglistexample.persistence.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class DbTypeConverters {

    @TypeConverter
    fun listFromCsv(csv: String?): List<String> =
        csv?.takeIf { it.isNotEmpty() }?.split(",") ?: emptyList()

    @TypeConverter
    fun csvFromList(list: List<String>) =
        list.joinToString(",")

}