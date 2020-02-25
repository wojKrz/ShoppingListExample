package pl.shoppinglistexample.presentation

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import pl.shoppinglistexample.presentation.main.di.MainModule

@Module(includes = [MainModule::class])
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
