package pl.shoppinglistexample.presentation.main.currentlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.shoppinglistexample.databinding.CurrentShoppingListItemBinding
import pl.shoppinglistexample.presentation.main.base.bindingOf

class ArchiveCurrentShoppingListItemEventHook(private val onArchiveClicked: (Long) -> Unit) :
    ClickEventHook<CurrentShoppingListItem>() {

    override fun onBind(viewHolder: RecyclerView.ViewHolder): View? =
        viewHolder
            .bindingOf<CurrentShoppingListItemBinding>()
            ?.archiveButton

    override fun onClick(
        v: View,
        position: Int,
        fastAdapter: FastAdapter<CurrentShoppingListItem>,
        item: CurrentShoppingListItem
    ) {
        onArchiveClicked(item.model.id)
    }
}
