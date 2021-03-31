package pl.shoppinglistexample.presentation.main.currentlist.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.shoppinglistexample.databinding.CreateNewListFragmentBinding
import pl.shoppinglistexample.presentation.main.base.ViewEvent
import pl.shoppinglistexample.presentation.main.base.event.EventConsumer

@AndroidEntryPoint
class CreateNewListFragment : DialogFragment() {

    val viewModel: CreateNewListViewModel by viewModels()

    private lateinit var binding: CreateNewListFragmentBinding

    sealed class CreateListViewEvent : ViewEvent() {
        object ListCreatedEvent : CreateListViewEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateNewListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getViewEvents().observe(viewLifecycleOwner, EventConsumer {
            reactToEvent(it)
        })
    }

    private fun reactToEvent(event: ViewEvent) {
        when (event) {
            is CreateListViewEvent.ListCreatedEvent -> findNavController().popBackStack()
        }
    }
}
