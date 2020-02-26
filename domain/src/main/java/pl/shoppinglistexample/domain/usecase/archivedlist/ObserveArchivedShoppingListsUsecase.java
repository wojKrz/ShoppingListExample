package pl.shoppinglistexample.domain.usecase.archivedlist;
import java.util.List;

import io.reactivex.Single;
import pl.shoppinglistexample.domain.model.ShoppingListItemModel;
import pl.shoppinglistexample.domain.model.ShoppingListModel;
import pl.shoppinglistexample.domain.usecase.base.FlowableUsecase;

public interface ObserveArchivedShoppingListsUsecase extends FlowableUsecase<Void, List<ShoppingListItemModel>> {
}
