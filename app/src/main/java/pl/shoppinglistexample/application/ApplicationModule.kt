package pl.shoppinglistexample.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideApplicationContext(application: Context): Context =
        application

}
