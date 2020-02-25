package pl.shoppinglistexample.domain.usecase.archivedlist;

import java.util.List;

import io.reactivex.Flowable;
import pl.shoppinglistexample.domain.model.ShoppingListModel;

public class ObserveArchivedShoppingListsUsecaseImpl implements ObserveArchivedShoppingListsUsecase {

    @Override
    public Flowable<List<ShoppingListModel>> execute(Void aVoid) {
        return null;
    }

}
