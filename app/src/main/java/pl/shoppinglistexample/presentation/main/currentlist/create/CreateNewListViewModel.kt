package pl.shoppinglistexample.presentation.main.currentlist.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import pl.shoppinglistexample.domain.usecase.currentlist.CreateNewListParams
import pl.shoppinglistexample.domain.usecase.currentlist.CreateNewShoppingListUsecase
import pl.shoppinglistexample.presentation.main.base.BaseViewModel
import pl.shoppinglistexample.presentation.main.currentlist.create.CreateNewListFragment.CreateListViewEvent.ListCreatedEvent
import javax.inject.Inject

class CreateNewListViewModel @Inject constructor(
    private val createNewShoppingListUsecase: CreateNewShoppingListUsecase
) : BaseViewModel() {

    val title = MutableLiveData<String>()

    fun onCreateClick() {
        createNewShoppingListUsecase.execute(
            CreateNewListParams( title.value ?: "", DateTime.now().millis))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCreationSuccess, ::onCreationError)
            .addTo(compositeDisposable)
    }

    private fun onCreationSuccess() {
        postConsumableActionEvent(ListCreatedEvent)
    }

    private fun onCreationError(err: Throwable) {
        Log.e("Creation error", err.message ?: "")
    }

}
