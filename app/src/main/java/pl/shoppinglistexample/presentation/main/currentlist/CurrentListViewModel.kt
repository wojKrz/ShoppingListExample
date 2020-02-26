package pl.shoppinglistexample.presentation.main.currentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.domain.usecase.currentlist.MoveListToArchivedUsecase
import pl.shoppinglistexample.domain.usecase.currentlist.ObserveCurrentShoppingListsUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.AddShoppingListItemUsecase
import pl.shoppinglistexample.presentation.main.base.BaseViewModel
import javax.inject.Inject

class CurrentListViewModel @Inject constructor(
    val observeCurrentShoppingListsUsecase: ObserveCurrentShoppingListsUsecase,
    val moveListToArchivedUsecase: MoveListToArchivedUsecase
): BaseViewModel() {

    private val _lists = MutableLiveData<List<ShoppingListItemModel>>()

    fun getLists(): LiveData<List<ShoppingListItemModel>> {
        observeCurrentShoppingListsUsecase.execute(null)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _lists.value = it
            }
            .addTo(compositeDisposable)
        return _lists
    }

    fun onAddNewListClick() {
        _viewEvents.value = CurrentListFragment.CurrentListViewEvent.ShowNewListFormEvent
    }

    fun onMoveToArchivedClick(listId: Long) {
        moveListToArchivedUsecase.execute(listId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onMoveToArchiveSuccess)
            .addTo(compositeDisposable)
    }

    private fun onMoveToArchiveSuccess() {
        _viewEvents.value = CurrentListFragment.CurrentListViewEvent.ShowArchiveSucccessInfo
    }

    fun onDisplayDetailsClick(listId: Long) {
        _viewEvents.value = CurrentListFragment.CurrentListViewEvent.ShowListDetails(listId)
    }

}
