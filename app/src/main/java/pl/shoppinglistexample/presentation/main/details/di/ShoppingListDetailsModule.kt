package pl.shoppinglistexample.presentation.main.details.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.shoppinglistexample.presentation.main.ViewModelKey
import pl.shoppinglistexample.presentation.main.details.ShoppingListDetailsFragment
import pl.shoppinglistexample.presentation.main.details.ShoppingListDetailsViewModel

@Module
abstract class ShoppingListDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingListDetailsViewModel::class)
    abstract fun bindShoppingListDetailsViewModel(viewModel: ShoppingListDetailsViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): ShoppingListDetailsFragment

}
