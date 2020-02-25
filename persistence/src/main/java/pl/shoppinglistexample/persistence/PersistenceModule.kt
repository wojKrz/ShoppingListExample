package pl.shoppinglistexample.persistence

import dagger.Module
import pl.shoppinglistexample.persistence.database.DatabaseModule

@Module(includes = [DatabaseModule::class])
class PersistenceModule