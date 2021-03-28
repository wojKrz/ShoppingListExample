package pl.shoppinglistexample.domain.usecase.listdetails;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper;
import pl.shoppinglistexample.domain.model.ShoppingListModel;
import pl.shoppinglistexample.domain.usecase.base.CompletableUsecase;
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao;

public class AddShoppingListItemUsecase implements CompletableUsecase<UpdateShoppingListParams.AddItemParams> {

    private ShoppingListDao shoppingListDao;
    private ShoppingListItemsMapper mapper;

    @Inject
    public AddShoppingListItemUsecase(ShoppingListDao shoppingListDao, ShoppingListItemsMapper mapper) {
        this.shoppingListDao = shoppingListDao;
        this.mapper = mapper;
    }

    @Override
    public Completable execute(UpdateShoppingListParams.AddItemParams addItemParams) {

        if(addItemParams.getItem() == null || addItemParams.getItem().isEmpty()) {
            return Completable.error(new InvalidParameterException("Item to add cannot be neither null nor empty"));
        }

        if(addItemParams.getListModel().isArchived()) {
            return Completable.error(new UnsupportedOperationException("Cannot update archived list with id " + addItemParams.getListModel().getId()));
        } else {
            List<String> newItemsList = new ArrayList<>(addItemParams.getListModel().getItems());
            newItemsList.add(addItemParams.getItem());

            ShoppingListModel updatedEntity = new ShoppingListModel(
                    addItemParams.getListModel().getId(),
                    addItemParams.getListModel().getTitle(),
                    addItemParams.getListModel().getTimestampCreated(),
                    newItemsList,
                    addItemParams.getListModel().isArchived()
            );

            return shoppingListDao.insertList(mapper.mapDomainToPersistence(updatedEntity));
        }
    }

}
