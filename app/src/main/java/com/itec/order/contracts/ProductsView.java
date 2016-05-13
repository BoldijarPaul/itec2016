package com.itec.order.contracts;

import com.itec.order.data.models.Product;

import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public interface ProductsView {
    void showProducts(List<Product> products);
}