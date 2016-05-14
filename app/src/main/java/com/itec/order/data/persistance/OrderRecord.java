package com.itec.order.data.persistance;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderRecord extends SugarRecord {
    public int orderId;
    public Date date;

    public List<OrderProductRecord> products;

    public OrderRecord() {
    }

    public OrderRecord(int orderId) {
        this.orderId = orderId;
        date = Calendar.getInstance().getTime();
        this.products = new ArrayList<>();
    }
}
