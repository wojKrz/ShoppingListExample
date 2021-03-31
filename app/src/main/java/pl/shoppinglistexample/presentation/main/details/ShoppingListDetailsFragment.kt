package pl.shoppinglistexample.presentation.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import pl.shoppinglistexample.databinding.ShoppingListDetailsFragmentBinding

@AndroidEntryPoint
class ShoppingListDetailsFragment : Fragment() {

    val viewModel: ShoppingListDetailsViewModel by viewModels()

    private lateinit var binding: ShoppingListDetailsFragmentBinding

    private val args: ShoppingListDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShoppingListDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.appBar.setupWithNavController(findNavController())

        viewModel.initWithId(args.listId)

        viewModel.getElements().observe(viewLifecycleOwner, {
            displayElements(mapToListItems(it))
        })

        viewModel.isEditable.observe(viewLifecycleOwner, {
            setupEditable(it)
        })
    }

    private fun setupEditable(isEditable: Boolean) {
        listItemFactory = { title: String -> ShoppingListElementItem(isEditable, title) }
    }

    private lateinit var listItemFactory: (String) -> ShoppingListElementItem

    private lateinit var listElementsAdapter: ItemAdapter<ShoppingListElementItem>

    private fun initRecyclerView() {
        listElementsAdapter = ItemAdapter()
        val fastAdapter = FastAdapter.with(listElementsAdapter).apply {
            addEventHook(ClickRemoveShoppingListElementEventHook(viewModel::onRemoveElementClick))
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.listItemsRecycler.apply {
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
