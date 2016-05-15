package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Paul on 5/13/2016.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.header_email)
    TextView mEmail;
    @Bind(R.id.header_logout)
    View mLogout;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
