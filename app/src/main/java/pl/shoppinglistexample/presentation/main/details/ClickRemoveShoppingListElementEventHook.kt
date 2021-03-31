package pl.shoppinglistexample.presentation.main.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.shoppinglistexample.databinding.ShoppingListElementItemBinding
import pl.shoppinglistexample.presentation.main.base.bindingOf

class ClickRemoveShoppingListElementEventHook(private val removeOnPositionListener: (Int) -> Unit): ClickEventHook<ShoppingListElementItem>() {

    override fun onBind(viewHolder: RecyclerView.ViewHolder): View? =
        viewHolder
            .bindingOf<ShoppingListElementItemBinding>()
            ?.removeElementButton

    override fun onClick(
        v: View,
        position: Int,
        fastAdapter: FastAdapter<ShoppingListElementItem>,
        item: ShoppingListElementItem
    ) {
        removeOnPositionListener(position)
    }
}
