package com.itec.order.data.persistance;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderRecord extends SugarRecord {
    public int orderId;
    public Date date;

    public OrderRecord() {
    }

    public OrderRecord(int orderId) {
        this.orderId = orderId;
        date = Calendar.getInstance().getTime();
    }
}
