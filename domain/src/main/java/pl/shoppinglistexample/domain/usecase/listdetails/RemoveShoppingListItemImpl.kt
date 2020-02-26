package pl.shoppinglistexample.domain.usecase.listdetails

import io.reactivex.Completable
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class RemoveShoppingListItemImpl @Inject constructor(
    val shoppingListDao: ShoppingListDao,
    val shoppingListMapper: ShoppingListItemsMapper
) : RemoveShoppingListItemUsecase {

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