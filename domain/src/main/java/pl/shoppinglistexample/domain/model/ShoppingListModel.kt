package pl.shoppinglistexample.domain.model

data class ShoppingListModel(val id: Long, val title: String, val timestampCreated: Long, val items: List<String>, val isArchived: Boolean)