package pl.shoppinglistexample.presentation.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_details_fragment.*
import kotlinx.android.synthetic.main.shopping_list_element_item.view.*
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.ListDetailsFragmentBinding
import pl.shoppinglistexample.presentation.main.base.BaseFragment

@AndroidEntryPoint
class ShoppingListDetailsFragment : BaseFragment() {

    val viewModel: ShoppingListDetailsViewModel by viewModels()

    private val args: ShoppingListDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<ListDetailsFragmentBinding>(
        inflater,
        R.layout.list_details_fragment,
        container,
        false
    ).run {
        viewModel = this@ShoppingListDetailsFragment.viewModel
        lifecycleOwner = this@ShoppingListDetailsFragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        appBar.setupWithNavController(findNavController())

        viewModel.initWithId(args.listId)

        viewModel.getElements().observe(viewLifecycleOwner, Observer {
            displayElements(mapToListItems(it))
        })

        viewModel.isEditable.observe(viewLifecycleOwner, Observer {
            setupEditable(it)
        })
    }

    private fun setupEditable(isEditable: Boolean) {
        listItemFactory = if (isEditable) {
            ::CurrentShoppingListElementItem
        } else {
            ::ArchivedShoppingListElementItem
        }
    }

    private val deleteItemEventHook = object : ClickEventHook<ShoppingListElementItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? =
            if (viewHolder is ShoppingListElementItem.ShoppingListElementViewHolder) {
                viewHolder.itemView.removeElementButton
            } else {
                null
            }

        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<ShoppingListElementItem>,
            item: ShoppingListElementItem
        ) {
            viewModel.onRemoveElementClick(position)
        }
    }

    private lateinit var listItemFactory: (String) -> ShoppingListElementItem

    private lateinit var listElementsAdapter: ItemAdapter<ShoppingListElementItem>

    private fun initRecyclerView() {
        listElementsAdapter = ItemAdapter()
        val fastAdapter = FastAdapter.with(listElementsAdapter).apply {
            addEventHook(deleteItemEventHook)
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        listItemsRecycler.apply {
            this.adapter = fastAdapter
            this.layoutManager = layoutManager
        }
    }

    private fun mapToListItems(state: ShoppingListDetailsViewModel.ListElementsViewState): List<ShoppingListElementItem> {
        return state.list.map { listItemFactory(it) }
    }

    private fun displayElements(elements: List<ShoppingListElementItem>) {
        listElementsAdapter.setNewList(elements)
    }
}
