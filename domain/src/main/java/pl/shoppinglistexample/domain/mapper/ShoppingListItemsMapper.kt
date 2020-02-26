package pl.shoppinglistexample.domain.mapper

import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.domain.model.ShoppingListModel
import pl.shoppinglistexample.persistence.database.entity.ShoppingListEntity
import javax.inject.Inject

class ShoppingListItemsMapper @Inject constructor() {

    fun mapDomainToPersistence(domainShoppingList: ShoppingListModel) = ShoppingListEntity(
        domainShoppingList.id,
        domainShoppingList.title,
        domainShoppingList.timestampCreated,
        domainShoppingList.items,
        domainShoppingList.isArchived
    )

    fun mapPersistenceToDomain(shoppingListEntity: ShoppingListEntity) =
        ShoppingListItemModel(
            shoppingListEntity.id ?: 0L,
            shoppingListEntity.title ?: "",
            shoppingListEntity.timestampCreated
        )

    fun mapPersistenceListToDomain(list: List<ShoppingListEntity>) =
        list.map(::mapPersistenceToDomain)
}