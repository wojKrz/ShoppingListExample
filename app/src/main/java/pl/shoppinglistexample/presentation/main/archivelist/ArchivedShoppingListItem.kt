package pl.shoppinglistexample.presentation.main.archivelist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.ArchivedShoppingListItemBinding
import pl.shoppinglistexample.domain.model.ShoppingListItemModel

class ArchivedShoppingListItem(model: ShoppingListItemModel) :
    ModelAbstractBindingItem<ShoppingListItemModel, ArchivedShoppingListItemBinding>(model) {

    override val type: Int
        get() = R.id.itemArchivedList

    override var identifier: Long
        get() = model.id
        set(_) {}

    override fun createBinding(
        inflater: LayoutInflater, parent: ViewGroup?
    ): ArchivedShoppingListItemBinding =
        ArchivedShoppingListItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ArchivedShoppingListItemBinding, payloads: List<Any>) =
        with(binding) {

            listTitleTxt.text = model.title
            creationDateTxt.text =
                DateTime(model.timestampCreated).toString(DateTimeFormat.forPattern("dd.MM.yyyy"))
        }

    override fun unbindView(binding: ArchivedShoppingListItemBinding) = with(binding) {
        listTitleTxt.text = null
        creationDateTxt.text = null
    }
}