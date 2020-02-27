package pl.shoppinglistexample.presentation.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import pl.shoppinglistexample.domain.mapper.ShoppingListMapper
import pl.shoppinglistexample.domain.model.ShoppingListModel
import pl.shoppinglistexample.domain.usecase.listdetails.AddShoppingListItemUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.ObserveShoppingListDetailsUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.RemoveShoppingListItemUsecase
import pl.shoppinglistexample.domain.usecase.listdetails.UpdateShoppingListParams
import pl.shoppinglistexample.presentation.main.base.BaseViewModel
import java.security.InvalidParameterException
import javax.inject.Inject

class ShoppingListDetailsViewModel @Inject constructor(
    private val observeShoppingListDetailsUsecase: ObserveShoppingListDetailsUsecase,
    private val addShoppingListItemUsecase: AddShoppingListItemUsecase,
    private val removeShoppingListItemUsecase: RemoveShoppingListItemUsecase
) : BaseViewModel() {

    private lateinit var currentShoppingListModel: ShoppingListModel

    fun initWithId(id: Long) {
        observeShoppingListDetailsUsecase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                currentShoppingListModel = it
                _isEditable.value = it.isArchived.not()
                _elements.value = ListElementsViewState(it.items, -1)
                _title.value = it.title
            }.addTo(compositeDisposable)
    }

    private val _isEditable = MutableLiveData<Boolean>()

    val isEditable : LiveData<Boolean> = _isEditable

    private val _elements = MutableLiveData<ListElementsViewState>()

    fun getElements(): LiveData<ListElementsViewState> = _elements

    private val _title = MutableLiveData<String>()

    val title: LiveData<String> = _title

    val newElement = MutableLiveData<String>()

    private val _errors = MutableLiveData<ShoppingListDetailsError>()

    val errors: LiveData<ShoppingListDetailsError> = _errors

    data class ListElementsViewState(val list: List<String>, val activeEditIndex: Int)

    fun onAddElementClick() {
        val params =
            UpdateShoppingListParams.AddItemParams(newElement.value, currentShoppingListModel)
        addShoppingListItemUsecase
            .execute(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newElement.value = ""
                _errors.value = ShoppingListDetailsError("")
            }, ::handleAddElementError)
            .addTo(compositeDisposable)
    }

    private fun handleAddElementError(err: Throwable) {
        when (err) {
            is InvalidParameterException -> _errors.value = ShoppingListDetailsError("err")
        }
    }

    data class ShoppingListDetailsError(
        val newItemError: String
    )

    fun onRemoveElementClick(index: Int) {

        val params = UpdateShoppingListParams.RemoveItemParams(index, currentShoppingListModel)
        removeShoppingListItemUsecase
            .execute(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)

    }

}
