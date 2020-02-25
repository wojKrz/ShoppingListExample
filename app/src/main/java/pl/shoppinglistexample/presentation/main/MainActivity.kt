package pl.shoppinglistexample.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import pl.shoppinglistexample.databinding.MainActivityBinding
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    //region ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    //endregion

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<MainActivityBinding>(this, pl.shoppinglistexample.R.layout.main_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    //endregion

}
