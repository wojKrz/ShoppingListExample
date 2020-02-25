package pl.shoppinglistexample.presentation.main.currentlist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.shoppinglistexample.presentation.main.ViewModelKey
import pl.shoppinglistexample.presentation.main.currentlist.CurrentListFragment
import pl.shoppinglistexample.presentation.main.currentlist.CurrentListViewModel

@Module
abstract class CurrentListModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentListViewModel::class)
    abstract fun bindCurrentListViewModel(viewModel: CurrentListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): CurrentListFragment

}
