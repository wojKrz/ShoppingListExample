package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Flowable
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class ObserveCurrentShoppingListImpl @Inject constructor(
    val shoppingListDao: ShoppingListDao,
    val shoppingListMapper: ShoppingListItemsMapper): ObserveCurrentShoppingListsUsecase {

    override fun execute(args: Void?): Flowable<List<ShoppingListItemModel>> =
        shoppingListDao.observeCurrentLists()
            .map(shoppingListMapper::mapPersistenceListToDomain)

}