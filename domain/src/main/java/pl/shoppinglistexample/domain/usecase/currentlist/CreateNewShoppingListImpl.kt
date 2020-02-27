package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CreateNewShoppingListImpl @Inject constructor(
    val shoppingListDao: ShoppingListDao
) : CreateNewShoppingListUsecase {

    override fun execute(args: CreateNewListParams): Completable = args.run {
        if (title.isBlank()) {
            Completable.error(IllegalArgumentException("Title cannot be empty"))
        } else {
            shoppingListDao.insertList(
                ShoppingListEntity(
                    title = title,
                    isArchived = false,
                    timestampCreated = timestampCreated
                )
            )
        }
    }

}
