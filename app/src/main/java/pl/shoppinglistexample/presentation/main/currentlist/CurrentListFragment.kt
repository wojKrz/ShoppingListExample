package pl.shoppinglistexample.presentation.main.currentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.listeners.addClickListener
import kotlinx.android.synthetic.main.current_list_fragment.*
import kotlinx.android.synthetic.main.current_shopping_list_item.view.*
import kotlinx.android.synthetic.main.home_fragment.*
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.CurrentListFragmentBinding
import pl.shoppinglistexample.presentation.main.base.BaseFragment
import pl.shoppinglistexample.presentation.main.base.ViewEvent
import pl.shoppinglistexample.presentation.main.base.event.EventConsumer
import pl.shoppinglistexample.presentation.main.home.HomeFragmentDirections
import javax.inject.Inject

class CurrentListFragment : BaseFragment() {

    val viewModel: CurrentListViewModel by viewModels { vmFactory }

    sealed class CurrentListViewEvent : ViewEvent() {
        object ShowNewListFormEvent: CurrentListViewEvent()
        data class ShowListDetails(val listId: Long): CurrentListViewEvent()
        object ShowArchiveSucccessInfo: CurrentListViewEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<CurrentListFragmentBinding>(
        inflater,
        R.layout.current_list_fragment,
        container,
        false
    ).run {
        this.viewModel = this@CurrentListFragment.viewModel
        this.lifecycleOwner = this@CurrentListFragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.getLists().observe(viewLifecycleOwner, Observer {
            displayLists(it.map(::CurrentShoppingListItem))
        })

        viewModel.getViewEvents().observe(viewLifecycleOwner, EventConsumer {
            reactToEvent(it)
        })
    }

    private fun reactToEvent(event: ViewEvent) {
        when(event) {
            is CurrentListViewEvent.ShowNewListFormEvent -> navigateToCreateList()
            is CurrentListViewEvent.ShowListDetails -> navigateToDetails(event.listId)
            is CurrentListViewEvent.ShowArchiveSucccessInfo -> showArchiveSuccessSnack()
        }
    }

    //region Navigation

    private fun navigateToCreateList() {
        findNavController().navigate(HomeFragmentDirections.actionCreateNewList())
    }

    private fun navigateToDetails(listId: Long) {
        findNavController().navigate(HomeFragmentDirections.actionCurrentToListDetails(listId))
    }

    //endregion

    private fun showArchiveSuccessSnack() {
        Snackbar.make(rootView, R.string.archived, Snackbar.LENGTH_SHORT).show()
    }

    private lateinit var currentListsFragmentAdapter: ItemAdapter<CurrentShoppingListItem>

    private val clickArchiveEventHook = object : ClickEventHook<CurrentShoppingListItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if(viewHolder is CurrentShoppingListItem.CurrentListViewHolder) {
                viewHolder.itemView.archiveButton
            } else {
                null
            }
        }

        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<CurrentShoppingListItem>,
            item: CurrentShoppingListItem
        ) {
            viewModel.onMoveToArchivedClick(item.model.id)
        }

    }

    private val clickItemEventHook = object : ClickEventHook<CurrentShoppingListItem>() {

        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if(viewHolder is CurrentShoppingListItem.CurrentListViewHolder) {
                viewHolder.itemView
            } else {
                return null
            }
        }

        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<CurrentShoppingListItem>,
            item: CurrentShoppingListItem
        ) {
            viewModel.onDisplayDetailsClick(item.model.id)
        }

    }

    private fun initRecyclerView() {

        currentListsFragmentAdapter = ItemAdapter()
        val fastadapter = FastAdapter.with(currentListsFragmentAdapter).apply {
            addEventHook(clickArchiveEventHook)
            addEventHook(clickItemEventHook)
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        shoppingListsRecycler.apply {
            this.adapter = fastadapter
            this.layoutManager = layoutManager
        }
    }

    private fun displayLists(items: List<CurrentShoppingListItem>) {
        currentListsFragmentAdapter.setNewList(items)
    }
}