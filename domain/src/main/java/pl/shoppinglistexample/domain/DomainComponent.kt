package pl.shoppinglistexample.domain

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecase
import pl.shoppinglistexample.domain.usecase.currentlist.CreateNewShoppingListUsecase
import pl.shoppinglistexample.domain.usecase.currentlist.MoveListToArchivedUsecase
import pl.shoppinglistexample.domain.usecase.currentlist.ObserveCurrentShoppingListsUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.AddShoppingListItemUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.ObserveShoppingListDetailsUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.RemoveShoppingListItemUsecase
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class]
)
interface DomainComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun domainInstance(context: Context): Builder

        fun build(): DomainComponent

    }


    fun getArchivedShoppingListUsecase(): ObserveArchivedShoppingListsUsecase

    fun getCurrentShoppingListsUsecase(): ObserveCurrentShoppingListsUsecase

    fun getAddShoppingListUsecas(): AddShoppingListItemUsecase

    fun getMoveListToArchivedUsecase(): MoveListToArchivedUsecase

    fun getRemoveShoppingListItemUsecase() : RemoveShoppingListItemUsecase

    fun getObserveShoppingListDetailsUsecase() : ObserveShoppingListDetailsUsecase

    fun getCreateNewShoppingListUsecase() : CreateNewShoppingListUsecase


}
