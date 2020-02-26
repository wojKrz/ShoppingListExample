package pl.shoppinglistexample.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import pl.shoppinglistexample.domain.DaggerDomainComponent
import pl.shoppinglistexample.domain.DomainComponent
import javax.inject.Inject

class ShoppingListApp : Application(), HasAndroidInjector {

    //region Activity injector

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

    //endregion

    //region Lifecycle
    override fun onCreate() {
        setupDI()
        super.onCreate()
    }

    //endregion

    //region DI

    private fun setupDI() {

        val domainComponent= DaggerDomainComponent.builder()
        .domainInstance(this)
            .build()

        DaggerApplicationComponent.builder()
            .appInstance(this)
            .domainInstance(domainComponent)
            .build()
            .also { it.inject(this) }
    }

    //endregion

}
