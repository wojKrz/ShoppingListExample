package pl.shoppinglistexample.domain.usecase.base;

public interface Usecase<Args, ReturnType> {

    ReturnType execute(Args args);

}
