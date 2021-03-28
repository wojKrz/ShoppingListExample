package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Completable
import pl.shoppinglistexample.domain.usecase.base.CompletableUsecase
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity
import javax.inject.Inject

data class CreateNewListParams(val title: String, val timestampCreated: Long)

class CreateNewShoppingList @Inject constructor(
    val shoppingListDao: ShoppingListDao
) : CompletableUsecase<CreateNewListParams> {

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
