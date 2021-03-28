package pl.shoppinglistexample.domain.usecase.listdetails

import io.reactivex.Completable
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.domain.usecase.base.CompletableUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.UpdateShoppingListParams.RemoveItemParams
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class RemoveShoppingListItem @Inject constructor(
    val shoppingListDao: ShoppingListDao,
    val shoppingListMapper: ShoppingListItemsMapper
) : CompletableUsecase<RemoveItemParams> {

    override fun execute(removeItemParams: UpdateShoppingListParams.RemoveItemParams): Completable {
        return if (removeItemParams.listModel.isArchived) run {
            Completable.error(
                UnsupportedOperationException("Cannot remove from archived list with id " + removeItemParams.listModel.id)
            )
        } else {
            val newItems = removeItemParams.listModel.items.toMutableList()
                .apply { removeAt(removeItemParams.itemPos) }
            shoppingListDao.insertList(
                shoppingListMapper.mapDomainToPersistence(
                    removeItemParams.currentShoppingListModel.copy(
                        items = newItems
                    )
                )
            )
        }
    }

}