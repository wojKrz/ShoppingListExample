package pl.shoppinglistexample.domain

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecase
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

    interface ComponentProvider {

        val domainComponent: DomainComponent

    }

    fun getArchivedShoppingListUsecase(): ObserveArchivedShoppingListsUsecase

}
