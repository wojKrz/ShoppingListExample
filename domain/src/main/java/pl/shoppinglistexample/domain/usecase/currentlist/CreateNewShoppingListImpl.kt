package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity
import javax.inject.Inject

class CreateNewShoppingListImpl @Inject constructor(
    val shoppingListDao: ShoppingListDao
): CreateNewShoppingListUsecase {

    override fun execute(args: CreateNewListParams): Completable = shoppingListDao.insertList(
        ShoppingListEntity(title = args.title, isArchived = false, timestampCreated = args.timestampCreated)
    ).subscribeOn(Schedulers.io())

}
