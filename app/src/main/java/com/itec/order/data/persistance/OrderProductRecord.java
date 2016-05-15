package com.itec.order.data.persistance;

import com.orm.SugarRecord;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderProductRecord extends SugarRecord {
    public String description;
    public int productId;
    public String image;
    public int categoryId;
    public String category;
    public int amount = 1;
    public int orderId;

    public OrderProductRecord() {
    }

    public OrderProductRecord(CurrentCartProduct currentCartProduct, int orderId) {
        description = currentCartProduct.description;
        categoryId = currentCartProduct.categoryId;
        category = currentCartProduct.category;
        productId = currentCartProduct.productId;
        image = currentCartProduct.image;
        amount = currentCartProduct.amount;
        this.orderId = orderId;
    }
}
