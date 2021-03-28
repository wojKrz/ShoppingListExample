package pl.shoppinglistexample.presentation.main.currentlist.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.CreateNewListFragmentBinding
import pl.shoppinglistexample.presentation.main.base.BaseDialogFragment
import pl.shoppinglistexample.presentation.main.base.ViewEvent
import pl.shoppinglistexample.presentation.main.base.event.EventConsumer

@AndroidEntryPoint
class CreateNewListFragment : BaseDialogFragment() {

    val viewModel: CreateNewListViewModel by viewModels()

    sealed class CreateListViewEvent : ViewEvent() {
        object ListCreatedEvent : CreateListViewEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<CreateNewListFragmentBinding>(
        inflater,
        R.layout.create_new_list_fragment,
        container,
        false
    ).run {
        this.viewModel = this@CreateNewListFragment.viewModel
        this.lifecycleOwner = this@CreateNewListFragment
        return root
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