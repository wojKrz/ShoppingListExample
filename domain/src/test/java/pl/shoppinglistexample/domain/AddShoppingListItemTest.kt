package pl.shoppinglistexample.domain

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.domain.model.ShoppingListModel
import pl.shoppinglistexample.domain.usecase.listdetails.AddShoppingListItemUsecaseImpl
import pl.shoppinglistexample.domain.usecase.listdetails.UpdateShoppingListParams
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import java.lang.UnsupportedOperationException

class AddShoppingListItemTest {

    @Rule @JvmField public var injectionRule : MockitoRule = MockitoJUnit.rule()

    @Mock lateinit var shoppingListDao: ShoppingListDao

    @Mock lateinit var mapper: ShoppingListItemsMapper

    @Test
    fun addShoppingListItem_failOnArchivedListUpdate() {
        val addShoppingListItemUsecase = AddShoppingListItemUsecaseImpl(shoppingListDao, mapper)

        val oldModel = ShoppingListModel(2, "title", 0L,emptyList(), true)
        val newItem = "New item"

        val params = UpdateShoppingListParams.AddItemParams(newItem, oldModel)

        addShoppingListItemUsecase.execute(params)
            .test()
            .assertError(UnsupportedOperationException::class.java)

    }
}