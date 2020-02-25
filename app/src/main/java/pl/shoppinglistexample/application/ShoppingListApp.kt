package pl.shoppinglistexample.application

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class ShoppingListApp : DaggerApplication(), ApplicationComponent.ComponentProvider {

    override lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationComponent

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

        applicationComponent = DaggerApplicationComponent.builder()
            .appInstance(this)
            .build()
            .also { it.inject(this) }
    }

    //endregion

}
