package pl.shoppinglistexample.presentation.main.archivelist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.shoppinglistexample.presentation.main.ViewModelKey
import pl.shoppinglistexample.presentation.main.archivelist.ArchivedListFragment
import pl.shoppinglistexample.presentation.main.archivelist.ArchivedListViewModel

@Module
abstract class ArchivedListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArchivedListViewModel::class)
    abstract fun bindArchivedListViewModel(viewModel: ArchivedListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): ArchivedListFragment

}
