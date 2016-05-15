package com.itec.order.ui.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itec.app.R;
import com.itec.order.contracts.PayPresenter;
import com.itec.order.contracts.PayView;
import com.itec.order.data.BillProduct;
import com.itec.order.data.models.PayBody;
import com.itec.order.ui.adapters.PayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment implements PayView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.pay_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private PayPresenter mPayPresenter;
    private PayAdapter mPayAdapter;

    public PayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Pay list");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPayAdapter = new PayAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPayAdapter);

        mPayPresenter = new PayPresenter(this);
        mPayPresenter.loadBillProducts();
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void clickedBill(final BillProduct billProduct) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to pay this one?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payBill(billProduct);
                    }
                })
                .setNegativeButton("No", null).show();
    }

    private void payBill(BillProduct billProduct) {
        int quantity = 1;
        List<PayBody> payBodies = new ArrayList<>();
        payBodies.add(new PayBody(billProduct.deliverOrderId, quantity));
        mPayPresenter.pay(payBodies);
    }

    @Override
    public void showProducts(List<BillProduct> billProducts) {
        mPayAdapter.setProducts(billProducts);
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.unknown_server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessPay() {
        Toast.makeText(getContext(), "Paid successfully", Toast.LENGTH_SHORT).show();
        mPayAdapter.setProducts(new ArrayList<BillProduct>());
        mPayPresenter.loadBillProducts();
    }

    @Override
    public void onRefresh() {
        mPayAdapter.setProducts(new ArrayList<BillProduct>());
        mPayPresenter.loadBillProducts();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
