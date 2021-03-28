package pl.shoppinglistexample.presentation.main.archivelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.ClickListener
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.current_list_fragment.*
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.ArchivedListFragmentBinding
import pl.shoppinglistexample.presentation.main.base.BaseFragment
import pl.shoppinglistexample.presentation.main.base.ViewEvent
import pl.shoppinglistexample.presentation.main.base.event.EventConsumer
import pl.shoppinglistexample.presentation.main.home.HomeFragmentDirections

@AndroidEntryPoint
class ArchivedListFragment : BaseFragment() {

    val viewModel: ArchivedListViewModel by viewModels()

    sealed class ArchivedListViewEvent : ViewEvent() {
        data class ShowListDetails(val listId: Long) : ArchivedListViewEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<ArchivedListFragmentBinding>(
        inflater,
        R.layout.archived_list_fragment,
        container,
        false
    ).run {
        lifecycleOwner = this@ArchivedListFragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            displayLists(it.map(::ArchivedShoppingListItem))
        })

        viewModel.getViewEvents().observe(viewLifecycleOwner, EventConsumer {
            reactToEvent(it)
        })

    }

    private fun reactToEvent(event: ViewEvent) {
        when (event) {
            is ArchivedListViewEvent.ShowListDetails -> navigateToDetails(event.listId)
        }
    }

    //region Navigation

    private fun navigateToDetails(listId: Long) {
        findNavController().navigate(HomeFragmentDirections.actionCurrentToListDetails(listId))
    }

    //endregion

    lateinit var archivedListsFragmentAdapter: ItemAdapter<ArchivedShoppingListItem>

    private fun initRecyclerView() {

        archivedListsFragmentAdapter = ItemAdapter()
        val fastadapter = FastAdapter.with(archivedListsFragmentAdapter).apply {
            onClickListener = object : ClickListener<ArchivedShoppingListItem> {
                override fun invoke(
                    v: View?,
                    adapter: IAdapter<ArchivedShoppingListItem>,
                    item: ArchivedShoppingListItem,
                    position: Int
                ): Boolean {
                    navigateToDetails(item.model.id)
                    return true
                }

            }
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        shoppingListsRecycler.apply {
            this.adapter = fastadapter
            this.layoutManager = layoutManager
        }
    }

    private fun displayLists(items: List<ArchivedShoppingListItem>) {
        archivedListsFragmentAdapter.setNewList(items)
    }
}
