package pl.shoppinglistexample.presentation.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.ShoppingListElementItemBinding

class ShoppingListElementItem(
    private val isEditable: Boolean,
    model: String
) : ModelAbstractBindingItem<String, ShoppingListElementItemBinding>(model) {

    override val type: Int
        get() = R.id.archivedShoppingListElement

    override var identifier: Long
        get() = model.hashCode().toLong()
        set(_) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ShoppingListElementItemBinding =
        ShoppingListElementItemBinding.inflate(inflater, parent, false)


    override fun bindView(binding: ShoppingListElementItemBinding, payloads: List<Any>) =
        with(binding) {
            itemTxt.text = model
            removeElementButton.isVisible = isEditable
        }

    override fun unbindView(binding: ShoppingListElementItemBinding) = with(binding) {
        itemTxt.text = null
        removeElementButton.isVisible = false
    }
}
