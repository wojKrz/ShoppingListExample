package pl.shoppinglistexample.domain.usecase.base;

import io.reactivex.Completable;

public interface CompletableUsecase<Args> extends Usecase<Args, Completable> {
}
