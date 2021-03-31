package pl.shoppinglistexample.presentation.main.currentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.CurrentShoppingListItemBinding
import pl.shoppinglistexample.domain.model.ShoppingListItemModel

class CurrentShoppingListItem(model: ShoppingListItemModel) :
    ModelAbstractBindingItem<ShoppingListItemModel, CurrentShoppingListItemBinding>(model) {

    override val type: Int
        get() = R.id.itemCurrentList

    override var identifier: Long
        get() = model.id
        set(_) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): CurrentShoppingListItemBinding =
        CurrentShoppingListItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: CurrentShoppingListItemBinding, payloads: List<Any>) =
        with(binding) {

            listTitleTxt.text = model.title
            creationDateTxt.text =
                DateTime(model.timestampCreated).toString(DateTimeFormat.forPattern("dd.MM.yyyy"))
        }

    override fun unbindView(binding: CurrentShoppingListItemBinding) = with(binding) {
        listTitleTxt.text = null
        creationDateTxt.text = null
    }

}
