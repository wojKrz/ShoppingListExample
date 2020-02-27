package pl.shoppinglistexample.presentation.main.currentlist

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.current_shopping_list_item.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import pl.shoppinglistexample.R
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import java.time.format.DateTimeFormatter

class CurrentShoppingListItem(model: ShoppingListItemModel) : ModelAbstractItem<ShoppingListItemModel, CurrentShoppingListItem.CurrentListViewHolder>(model) {

    override val layoutRes: Int
        get() = R.layout.current_shopping_list_item

    override val type: Int
        get() = R.id.itemCurrentList

    override var identifier: Long
        get() = model.id
        set(_) {}

    override fun getViewHolder(v: View): CurrentListViewHolder = CurrentListViewHolder(v)

    class CurrentListViewHolder(itemView: View): FastAdapter.ViewHolder<CurrentShoppingListItem>(itemView) {

        override fun bindView(item: CurrentShoppingListItem, payloads: MutableList<Any>) = with(itemView){

            listTitleTxt.text = item.model.title
            creationDateTxt.text = DateTime(item.model.timestampCreated).toString(DateTimeFormat.forPattern("dd.MM.yyyy"))
        }

        override fun unbindView(item: CurrentShoppingListItem) = with(itemView){
            listTitleTxt.text = null
            creationDateTxt.text = null
        }

    }
}

