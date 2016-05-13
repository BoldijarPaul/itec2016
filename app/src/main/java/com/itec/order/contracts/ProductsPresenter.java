package com.itec.order.contracts;


import com.itec.order.data.models.Product;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductsPresenter extends Presenter<ProductsView> {

    public ProductsPresenter(ProductsView view) {
        super(view);
    }

    public void loadProducts() {
        getView().showProducts(Product.mockList(100));
    }
}
