package pl.shoppinglistexample.domain.usecase.currentlist;

import java.util.List;

import pl.shoppinglistexample.domain.model.ShoppingListModel;
import pl.shoppinglistexample.domain.usecase.base.FlowableUsecase;

public interface ObserveCurrentShoplistsUsecase extends FlowableUsecase<Void, List<ShoppingListModel>> {
}
