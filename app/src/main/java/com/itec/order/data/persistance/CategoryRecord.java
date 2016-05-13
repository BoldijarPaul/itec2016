package com.itec.order.data.persistance;

import com.itec.order.data.models.Category;
import com.orm.SugarRecord;

/**
 * Created by Paul on 5/13/2016.
 */
public class CategoryRecord extends SugarRecord {
    int categoryId;
    String image;
    String description;

    public CategoryRecord() {
    }

    public CategoryRecord(Category category) {
        categoryId = category.id;
        image = category.image;
        if (category.description != null) {
            description = category.description.trim();
        }
    }
}
