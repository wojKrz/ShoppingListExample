package pl.shoppinglistexample.presentation.main.currentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.CurrentListFragmentBinding
import pl.shoppinglistexample.presentation.main.base.ViewEvent
import pl.shoppinglistexample.presentation.main.base.event.EventConsumer
import pl.shoppinglistexample.presentation.main.home.HomeFragmentDirections

@AndroidEntryPoint
class CurrentListFragment : Fragment() {

    val viewModel: CurrentListViewModel by viewModels()

    private lateinit var binding: CurrentListFragmentBinding

    sealed class CurrentListViewEvent : ViewEvent() {
        object ShowNewListFormEvent : CurrentListViewEvent()
        data class ShowListDetails(val listId: Long) : CurrentListViewEvent()
        object ShowArchiveSucccessInfo : CurrentListViewEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.getLists().observe(viewLifecycleOwner, {
            displayLists(it.map(::CurrentShoppingListItem))
        })

        viewModel.getViewEvents().observe(viewLifecycleOwner, EventConsumer {
            reactToEvent(it)
        })
    }

    private fun reactToEvent(event: ViewEvent) {
        when (event) {
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
        Snackbar.make(binding.rootView, R.string.archived, Snackbar.LENGTH_SHORT).show()
    }

    private lateinit var currentListsFragmentAdapter: ItemAdapter<CurrentShoppingListItem>

    private fun initRecyclerView() {

        currentListsFragmentAdapter = ItemAdapter()
        val fastadapter = FastAdapter.with(currentListsFragmentAdapter).apply {
            addEventHook(ArchiveCurrentShoppingListItemEventHook(viewModel::onMoveToArchivedClick))
            addEventHook(ClickCurrentShoppingItemEventHook(viewModel::onDisplayDetailsClick))
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.shoppingListsRecycler.apply {
            this.adapter = fastadapter
            this.layoutManager = layoutManager
        }
    }

    private fun displayLists(items: List<CurrentShoppingListItem>) {
        currentListsFragmentAdapter.setNewList(items)
    }
}