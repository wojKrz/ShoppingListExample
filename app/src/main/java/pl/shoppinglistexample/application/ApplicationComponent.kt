package pl.shoppinglistexample.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pl.shoppinglistexample.presentation.PresentationModule

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        PresentationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ShoppingListApp> {

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun appInstance(application: ShoppingListApp): Builder

        abstract fun build(): ApplicationComponent

    }

    interface ComponentProvider {

        val applicationComponent: ApplicationComponent

    }

}
