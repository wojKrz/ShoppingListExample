package pl.shoppinglistexample.domain.usecase.base;

import io.reactivex.Flowable;

public interface FlowableUsecase<Args, Result> extends Usecase<Args, Flowable<Result>> {
}
