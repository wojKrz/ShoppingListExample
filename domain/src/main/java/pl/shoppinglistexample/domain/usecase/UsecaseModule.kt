package pl.shoppinglistexample.domain.usecase

import dagger.Module
import dagger.Provides
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper
import pl.shoppinglistexample.domain.mapper.ShoppingListMapper
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecase
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecaseImpl
import pl.shoppinglistexample.domain.usecase.currentlist.*
import pl.shoppinglistexample.domain.usecase.listdetails.*
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Provides
    @Singleton
    internal fun provideObserveArchivedShoppingListsUsecase(
        shoppingListDao: ShoppingListDao,
        shoppingListMapper: ShoppingListItemsMapper
    ): ObserveArchivedShoppingListsUsecase =
        ObserveArchivedShoppingListsUsecaseImpl(shoppingListDao, shoppingListMapper)

    @Provides
    @Singleton
    internal fun provideObserveCurrentShoppingListsUsecase(
        shoppingListDao: ShoppingListDao,
        shoppingListMapper: ShoppingListItemsMapper
    ): ObserveCurrentShoppingListsUsecase =
        ObserveCurrentShoppingListImpl(shoppingListDao, shoppingListMapper)

    @Provides
    @Singleton
    internal fun provideAddShoppingListItemUsecase(
        shoppingListDao: ShoppingListDao,
        shoppingListMapper: ShoppingListItemsMapper
    ): AddShoppingListItemUsecase =
        AddShoppingListItemUsecaseImpl(shoppingListDao, shoppingListMapper)

    @Provides
    @Singleton
    internal fun provideMoveListToArchivedUsecase(
        shoppingListDao: ShoppingListDao
    ): MoveListToArchivedUsecase =
        MoveListToArchivedImpl(shoppingListDao)

    @Provides
    @Singleton
    internal fun provideRemoveShoppingListItemUsecase(
        shoppingListDao: ShoppingListDao,
        shoppingListMapper: ShoppingListItemsMapper
    ): RemoveShoppingListItemUsecase =
        RemoveShoppingListItemImpl(shoppingListDao, shoppingListMapper)

    @Provides
    @Singleton
    internal fun provideObserveShoppingListDetailsUsecase(
        shoppingListDao: ShoppingListDao,
        shoppingListMapper: ShoppingListMapper
    ): ObserveShoppingListDetailsUsecase =
        ObserveShoppingListDetailsImpl(shoppingListDao, shoppingListMapper)

    @Provides
    @Singleton
    internal fun provideCreateNewShoppingListUsecase(
        shoppingListDao: ShoppingListDao
    ): CreateNewShoppingListUsecase =
        CreateNewShoppingListImpl(shoppingListDao)


}
