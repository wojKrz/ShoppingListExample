package pl.shoppinglistexample.domain.usecase.listdetails

import io.reactivex.Flowable
import pl.shoppinglistexample.domain.mapper.ShoppingListMapper
import pl.shoppinglistexample.domain.model.ShoppingListModel
import pl.shoppinglistexample.domain.usecase.base.FlowableUsecase
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class ObserveShoppingListDetails @Inject constructor(
    val shoppingListDao: ShoppingListDao,
    val shoppingListMapper: ShoppingListMapper
): FlowableUsecase<Long, ShoppingListModel> {

    override fun execute(args: Long): Flowable<ShoppingListModel> =
        shoppingListDao
            .observeListWithId(args)
            .map(shoppingListMapper::mapPersistenceToDomain)
}