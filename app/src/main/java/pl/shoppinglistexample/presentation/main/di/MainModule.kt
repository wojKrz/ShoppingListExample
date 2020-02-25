package pl.shoppinglistexample.presentation.main.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.shoppinglistexample.presentation.main.MainActivity
import pl.shoppinglistexample.presentation.main.MainViewModel
import pl.shoppinglistexample.presentation.main.ViewModelKey
import pl.shoppinglistexample.presentation.main.archivelist.di.ArchivedListModule
import pl.shoppinglistexample.presentation.main.currentlist.di.CurrentListModule
import pl.shoppinglistexample.presentation.main.details.di.ShoppingListDetailsModule
import pl.shoppinglistexample.presentation.main.home.di.HomeModule

@Module(
    includes = [
        HomeModule::class,
        ArchivedListModule::class,
        CurrentListModule::class,
        ShoppingListDetailsModule::class
    ]
)
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): MainActivity

}
