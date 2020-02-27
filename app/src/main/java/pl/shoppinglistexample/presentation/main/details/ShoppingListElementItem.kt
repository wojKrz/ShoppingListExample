package pl.shoppinglistexample.presentation.main.details

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.shopping_list_element_item.view.*
import pl.shoppinglistexample.R

class CurrentShoppingListElementItem(model: String) : ShoppingListElementItem(model) {

    override val layoutRes: Int
        get() = R.layout.shopping_list_element_item
    override val type: Int
        get() = R.id.currentShoppingListElement

}

class ArchivedShoppingListElementItem(model: String) : ShoppingListElementItem(model){

    override val layoutRes: Int
        get() = R.layout.archived_shopping_list_element_item
    override val type: Int
        get() = R.id.archivedShoppingListElement

}

abstract class ShoppingListElementItem(model: String) : ModelAbstractItem<String, ShoppingListElementItem.ShoppingListElementViewHolder>(model) {

    override fun getViewHolder(v: View): ShoppingListElementViewHolder = ShoppingListElementViewHolder(v)

    class ShoppingListElementViewHolder(v: View): FastAdapter.ViewHolder<ShoppingListElementItem>(v) {

        override fun bindView(item: ShoppingListElementItem, payloads: MutableList<Any>) = with(itemView){
            itemTxt.text = item.model
        }

        override fun unbindView(item: ShoppingListElementItem) = with(itemView){
            itemTxt.text = null
        }

    }
}
