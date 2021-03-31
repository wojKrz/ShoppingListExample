package pl.shoppinglistexample.presentation.main.currentlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.shoppinglistexample.databinding.CurrentShoppingListItemBinding
import pl.shoppinglistexample.presentation.main.base.bindingOf

class ClickCurrentShoppingItemEventHook(
    private val onClickListener: (Long) -> Unit
) : ClickEventHook<CurrentShoppingListItem>() {

    override fun onBind(viewHolder: RecyclerView.ViewHolder): View? =
        viewHolder
            .bindingOf<CurrentShoppingListItemBinding>()
            ?.root

    override fun onClick(
        v: View,
        position: Int,
        fastAdapter: FastAdapter<CurrentShoppingListItem>,
        item: CurrentShoppingListItem
    ) {
        onClickListener(item.model.id)
    }
}