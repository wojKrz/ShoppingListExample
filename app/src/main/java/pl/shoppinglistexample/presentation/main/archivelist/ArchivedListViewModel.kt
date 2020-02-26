package pl.shoppinglistexample.presentation.main.archivelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.domain.model.ShoppingListItemModel
import pl.shoppinglistexample.domain.usecase.archivedlist.ObserveArchivedShoppingListsUsecase
import pl.shoppinglistexample.presentation.main.base.BaseViewModel
import javax.inject.Inject

class ArchivedListViewModel @Inject constructor(
    private val observeArchivedShoppingListsUsecase: ObserveArchivedShoppingListsUsecase
): BaseViewModel() {

    private val _items = MutableLiveData<List<ShoppingListItemModel>>()

    fun getItems(): LiveData<List<ShoppingListItemModel>> {
        observeArchivedShoppingListsUsecase.execute(null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _items.value = it
            }.addTo(compositeDisposable)
        return _items
    }



}
