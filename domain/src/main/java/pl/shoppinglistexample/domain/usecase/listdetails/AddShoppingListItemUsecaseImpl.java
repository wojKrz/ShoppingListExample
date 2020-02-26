package pl.shoppinglistexample.domain.usecase.listdetails;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper;
import pl.shoppinglistexample.domain.model.ShoppingListModel;
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao;

public class AddShoppingListItemUsecaseImpl implements AddShoppingListItemUsecase {

    ShoppingListDao shoppingListDao;
    ShoppingListItemsMapper mapper;

    @Inject
    public AddShoppingListItemUsecaseImpl(ShoppingListDao shoppingListDao, ShoppingListItemsMapper mapper) {
        this.shoppingListDao = shoppingListDao;
        this.mapper = mapper;
    }

    @Override
    public Completable execute(UpdateShoppingListParams.AddItemParams addItemParams) {
        if(addItemParams.getListModel().isArchived()) {
            return Completable.error(new UnsupportedOperationException("Cannot update archived list with id " + addItemParams.getListModel().getId()));
        } else {
            List<String> newItemsList = new ArrayList<>(addItemParams.getListModel().getItems());
            newItemsList.add(addItemParams.getItem());

            ShoppingListModel updatedEntity = new ShoppingListModel(
                    addItemParams.getListModel().getId(),
                    addItemParams.getListModel().getTitle(),
                    0L,
                    newItemsList,
                    addItemParams.getListModel().isArchived()
            );

            return shoppingListDao.insertList(mapper.mapDomainToPersistence(updatedEntity));
        }
    }

}
