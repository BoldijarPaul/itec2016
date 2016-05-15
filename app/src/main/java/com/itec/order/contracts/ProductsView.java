package com.itec.order.contracts;

import com.itec.order.data.models.Product;
import com.itec.order.data.persistance.FullProductRecord;

import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public interface ProductsView {
    void showProducts(List<FullProductRecord> products);
}