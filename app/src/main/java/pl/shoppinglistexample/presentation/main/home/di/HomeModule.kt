package pl.shoppinglistexample.presentation.main.home.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.shoppinglistexample.presentation.main.home.HomeFragment

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): HomeFragment

}
