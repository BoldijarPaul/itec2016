package com.itec.order.contracts;

/**
 * Created by Paul on 5/13/2016.
 */
public abstract class Presenter<T> {
    private T mView;

    public Presenter(T view) {
        this.mView = view;
    }

    public T getView() {
        return mView;
    }
}
