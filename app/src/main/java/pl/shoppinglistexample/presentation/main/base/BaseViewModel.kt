package pl.shoppinglistexample.presentation.main.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected val _viewEvents = MutableLiveData<ViewEvent>()

    fun getViewEvents(): LiveData<out ViewEvent> = _viewEvents

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}