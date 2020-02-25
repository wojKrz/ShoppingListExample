package pl.shoppinglistexample.domain

import dagger.Module
import pl.shoppinglistexample.domain.usecase.UsecaseModule
import pl.shoppinglistexample.persistence.PersistenceModule

@Module(includes = [UsecaseModule::class, PersistenceModule::class])
class DomainModule
