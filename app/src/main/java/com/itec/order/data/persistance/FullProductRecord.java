package com.itec.order.data.persistance;

import com.itec.order.data.models.Product;
import com.orm.SugarRecord;

/**
 * Created by Paul on 5/13/2016.
 */
public class FullProductRecord extends SugarRecord {
    String description;
    int productId;
    String image;
    String categoryId;
    String category;

    public FullProductRecord() {
    }

    public FullProductRecord(Product product) {
        description = product.description;
        productId = product.id;
        image = product.image;

    }

}
