package pl.shoppinglistexample.domain.usecase.archivedlist;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper;
import pl.shoppinglistexample.domain.model.ShoppingListItemModel;
import pl.shoppinglistexample.domain.usecase.base.FlowableUsecase;
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao;

public class ObserveArchivedShoppingListsUsecase implements FlowableUsecase<Void, List<ShoppingListItemModel>> {

    private ShoppingListDao shoppingListDao;
    private ShoppingListItemsMapper mapper;

    @Inject
    public ObserveArchivedShoppingListsUsecase(ShoppingListDao shoppingListDao,
                                               ShoppingListItemsMapper mapper) {
        this.shoppingListDao = shoppingListDao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<ShoppingListItemModel>> execute(Void aVoid) {
        return shoppingListDao.observeArchivedLists().map(mapper::mapPersistenceListToDomain);
    }

}
