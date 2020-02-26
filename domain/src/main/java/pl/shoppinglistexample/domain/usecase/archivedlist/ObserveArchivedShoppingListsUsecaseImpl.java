package pl.shoppinglistexample.domain.usecase.archivedlist;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import pl.shoppinglistexample.domain.mapper.ShoppingListItemsMapper;
import pl.shoppinglistexample.domain.model.ShoppingListItemModel;
import pl.shoppinglistexample.domain.model.ShoppingListModel;
import pl.shoppinglistexample.persistence.database.dao.ShoppingListDao;

public class ObserveArchivedShoppingListsUsecaseImpl implements ObserveArchivedShoppingListsUsecase {

    private ShoppingListDao shoppingListDao;
    private ShoppingListItemsMapper mapper;

    @Inject
    public ObserveArchivedShoppingListsUsecaseImpl(ShoppingListDao shoppingListDao,
                                                   ShoppingListItemsMapper mapper) {
        this.shoppingListDao = shoppingListDao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<ShoppingListItemModel>> execute(Void aVoid) {
        return shoppingListDao.observeArchivedLists().map(mapper::mapPersistenceListToDomain);
    }

}
