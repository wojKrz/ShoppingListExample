package pl.shoppinglistexample.domain.usecase.currentlist

import io.reactivex.Completable
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao

class MoveListToArchivedImpl constructor(
    val shoppingListDao: ShoppingListDao
) : MoveListToArchivedUsecase {

    override fun execute(args: Long): Completable =
        shoppingListDao.setIsArchived(args, true)

}
