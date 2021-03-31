package pl.shoppinglistexample.presentation.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.HomeFragmentBinding
import pl.shoppinglistexample.presentation.main.archivelist.ArchivedListFragment
import pl.shoppinglistexample.presentation.main.currentlist.CurrentListFragment

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeTabsAdapter(childFragmentManager, lifecycle)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = adapter.getLabelForElement(position)?.let { getString(it) }
        }
            .attach()
    }
}

class HomeTabsAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {

    private val tabLabels =
        mapOf(0 to R.string.current_list_label, 1 to R.string.archived_list_label)

    private val fragments = listOf(::CurrentListFragment, ::ArchivedListFragment)

    init {
        if (tabLabels.size != fragments.size) {
            Log.e("HomeTabs", "Number of labels doesn't match number of adapter fragments")
        }
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]()

    @StringRes
    fun getLabelForElement(pos: Int): Int? =
        tabLabels[pos]

}
