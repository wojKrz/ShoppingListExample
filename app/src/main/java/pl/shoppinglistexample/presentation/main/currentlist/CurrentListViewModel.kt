package pl.shoppinglistexample.presentation.main.currentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.domain.usecase.currentlist.MoveListToArchived
import pl.shoppinglistexample.domain.usecase.currentlist.ObserveCurrentShoppingList
import pl.shoppinglistexample.presentation.main.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentListViewModel @Inject constructor(
    private val observeCurrentShoppingListsUsecase: ObserveCurrentShoppingList,
    private val moveListToArchivedUsecase: MoveListToArchived
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
        postConsumableActionEvent(CurrentListFragment.CurrentListViewEvent.ShowNewListFormEvent)
    }

    fun onMoveToArchivedClick(listId: Long) {
        moveListToArchivedUsecase.execute(listId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onMoveToArchiveSuccess)
            .addTo(compositeDisposable)
    }

    private fun onMoveToArchiveSuccess() {
        postConsumableActionEvent(CurrentListFragment.CurrentListViewEvent.ShowArchiveSucccessInfo)
    }

    fun onDisplayDetailsClick(listId: Long) {
        postConsumableActionEvent(CurrentListFragment.CurrentListViewEvent.ShowListDetails(listId))
    }

}
