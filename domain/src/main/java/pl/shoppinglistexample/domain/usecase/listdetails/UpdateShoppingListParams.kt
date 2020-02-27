package pl.shoppinglistexample.domain.usecase.listdetails

import pl.shoppinglistexample.domain.model.ShoppingListModel

sealed class UpdateShoppingListParams(val currentShoppingListModel: ShoppingListModel) {
    data class RemoveItemParams(val itemPos: Int, val listModel: ShoppingListModel)
        : UpdateShoppingListParams(listModel)
    data class AddItemParams(val item: String?, val listModel: ShoppingListModel)
}