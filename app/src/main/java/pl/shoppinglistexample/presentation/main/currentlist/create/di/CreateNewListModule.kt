package pl.shoppinglistexample.presentation.main.currentlist.create.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.shoppinglistexample.presentation.main.ViewModelKey
import pl.shoppinglistexample.presentation.main.currentlist.create.CreateNewListFragment
import pl.shoppinglistexample.presentation.main.currentlist.create.CreateNewListViewModel

@Module
abstract class CreateNewListModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateNewListViewModel::class)
    abstract fun bindCreateNewListViewModel(viewModel: CreateNewListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjetor(): CreateNewListFragment

}
