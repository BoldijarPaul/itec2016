package com.itec.order.data.persistance;

import com.orm.SugarRecord;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderProductModel extends SugarRecord {
    public String description;
    public int productId;
    public String image;
    public int categoryId;
    public String category;
    public int amount = 1;

    public OrderProductModel() {
    }

    public OrderProductModel(CurrentCartProduct currentCartProduct) {
        description = currentCartProduct.description;
        categoryId = currentCartProduct.categoryId;
        category = currentCartProduct.category;
        productId = currentCartProduct.productId;
        image = currentCartProduct.image;
        amount = currentCartProduct.amount;
    }
}
