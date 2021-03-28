package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Flowable
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.domain.usecase.base.FlowableUsecase
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class ObserveCurrentShoppingList @Inject constructor(
    val shoppingListDao: ShoppingListDao,
    val shoppingListMapper: ShoppingListItemsMapper
) : FlowableUsecase<Void, List<ShoppingListItemModel>> {

    override fun execute(args: Void?): Flowable<List<ShoppingListItemModel>> =
        shoppingListDao.observeCurrentLists()
            .map(shoppingListMapper::mapPersistenceListToDomain)

}