package pl.shoppinglistexample.presentation.main.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import pl.shoppinglistexample.presentation.main.base.event.ConsumableEvent

abstract class BaseViewModel: ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected val _viewEvents = MutableLiveData<ConsumableEvent<ViewEvent>>()

    fun getViewEvents(): LiveData<ConsumableEvent<ViewEvent>> = _viewEvents

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun postConsumableActionEvent(event: ViewEvent) {
        _viewEvents.value = ConsumableEvent(event)
    }

}
