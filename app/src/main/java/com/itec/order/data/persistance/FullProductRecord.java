package com.itec.order.data.persistance;

import com.itec.order.data.models.Product;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class FullProductRecord extends SugarRecord {
    public String description;
    public int productId;
    public String image;
    public int categoryId;
    public String category;

    public FullProductRecord() {
    }

    public FullProductRecord(Product product) {
        description = product.description;
        productId = product.id;
        image = product.image;
        categoryId = product.categoryId;
        List<CategoryRecord> categoryRecordList = CategoryRecord.find(
                CategoryRecord.class, "category_id = ?",
                String.valueOf(categoryId));
        if (categoryRecordList.size() > 0) {
            category = categoryRecordList.get(0).description;
        }

    }

}
