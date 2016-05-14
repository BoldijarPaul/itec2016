package com.itec.order.data.persistance;

import com.orm.SugarRecord;

/**
 * Created by Paul on 5/14/2016.
 */
public class CurrentCartProduct extends SugarRecord {
    public String description;
    public int productId;
    public String image;
    public int categoryId;
    public String category;
    public int amount = 1;

    public CurrentCartProduct() {
    }

    public CurrentCartProduct(FullProductRecord fullProductRecord) {
        description = fullProductRecord.description;
        categoryId = fullProductRecord.categoryId;
        category = fullProductRecord.category;
        productId = fullProductRecord.productId;
        image = fullProductRecord.image;
        amount = 1;
    }
}
