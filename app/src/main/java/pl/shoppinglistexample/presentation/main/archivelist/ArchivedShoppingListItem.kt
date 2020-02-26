package pl.shoppinglistexample.presentation.main.archivelist

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem
import kotlinx.android.synthetic.main.current_shopping_list_item.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import pl.shoppinglistexample.R
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.presentation.main.currentlist.CurrentShoppingListItem

class ArchivedShoppingListItem (model: ShoppingListItemModel) : ModelAbstractItem<ShoppingListItemModel, ArchivedShoppingListItem.ArchivedListViewHolder>(model) {

    override val layoutRes: Int
        get() = R.layout.archived_shopping_list_item

    override val type: Int
        get() = R.id.itemArchivedList

    override var identifier: Long
        get() = model.id
        set(_) {}

    override fun getViewHolder(v: View): ArchivedListViewHolder = ArchivedListViewHolder(v)

    class ArchivedListViewHolder(v: View) : FastAdapter.ViewHolder<ArchivedShoppingListItem>(v) {

        override fun bindView(item: ArchivedShoppingListItem, payloads: MutableList<Any>) = with(itemView){

            listTitleTxt.text = item.model.title
            creationDateTxt.text = DateTime(item.model.timestampCreated).toString(DateTimeFormat.forPattern("dd.MM.yyyy"))
        }

        override fun unbindView(item: ArchivedShoppingListItem) = with(itemView){
            listTitleTxt.text = null
            creationDateTxt.text = null
        }


    }
}