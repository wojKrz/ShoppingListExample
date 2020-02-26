package pl.shoppinglistexample.presentation.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment.*
import pl.shoppinglistexample.R
import pl.shoppinglistexample.databinding.HomeFragmentBinding
import pl.shoppinglistexample.presentation.main.archivelist.ArchivedListFragment
import pl.shoppinglistexample.presentation.main.base.BaseFragment
import pl.shoppinglistexample.presentation.main.currentlist.CurrentListFragment

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(
        DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )
    ) {

        lifecycleOwner = this@HomeFragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeTabsAdapter(this)
        viewpager.adapter = adapter

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = adapter.getLabelForElement(position)?.let { getString(it) }
        }.attach()
    }
}

class HomeTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val tabLabels =
        mapOf(0 to R.string.current_list_label, 1 to R.string.archived_list_label)

    private val fragments = listOf(::CurrentListFragment, ::ArchivedListFragment)

    init {
        if(tabLabels.size != fragments.size) {
            Log.e("HomeTabs", "Number of labels doesn't match number of adapter fragments")
        }
    }
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]()

    @StringRes
    fun getLabelForElement(pos: Int): Int? =
        tabLabels[pos]

}
