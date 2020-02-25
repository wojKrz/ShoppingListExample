package pl.shoppinglistexample.domain.usecase

import dagger.Module
import dagger.Provides
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecase
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecaseImpl
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Provides
    @Singleton
    internal fun provideGetArchivedShoppingListsUsecase(): ObserveArchivedShoppingListsUsecase =
        ObserveArchivedShoppingListsUsecaseImpl()
}