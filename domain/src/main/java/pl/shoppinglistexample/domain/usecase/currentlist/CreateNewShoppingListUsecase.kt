package pl.shoppinglistexample.domain.usecase.currentlist

import pl.shoppinglistexample.domain.usecase.base.CompletableUsecase

interface CreateNewShoppingListUsecase: CompletableUsecase<CreateNewListParams>

data class CreateNewListParams(val title: String, val timestampCreated: Long)