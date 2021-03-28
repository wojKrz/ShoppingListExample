package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Completable
import pl.shoppinglistexample.domain.usecase.base.CompletableUsecase
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Inject

class MoveListToArchived @Inject constructor(
    val shoppingListDao: ShoppingListDao
) : CompletableUsecase<Long> {

    override fun execute(args: Long): Completable =
        shoppingListDao.setIsArchived(args, true)

}
