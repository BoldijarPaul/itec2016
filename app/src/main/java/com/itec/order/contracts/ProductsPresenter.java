package com.itec.order.contracts;


import com.itec.order.data.persistance.FullProductRecord;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductsPresenter extends Presenter<ProductsView> {

    public ProductsPresenter(ProductsView view) {
        super(view);
    }

    public void loadAllProducts() {
        getView().showProducts(FullProductRecord.listAll(FullProductRecord.class, "description asc"));
    }
}
