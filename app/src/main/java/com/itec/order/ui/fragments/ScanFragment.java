package com.itec.order.ui.fragments;


import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.itec.app.R;
import com.itec.order.contracts.ScanPresenter;
import com.itec.order.contracts.ScanView;
import com.itec.order.ui.app.BaseApp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends NfcFragment implements QRCodeReaderView.OnQRCodeReadListener, ScanView {

    @Bind(R.id.scan_qrcamera)
    QRCodeReaderView mQRCodeReaderView;
    @Bind(R.id.scan_main_layout)
    View mMainView;
    @Bind(R.id.scan_status)
    View mTableFoundView;

    private ScanPresenter mScanPresenter;

    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mQRCodeReaderView.setOnQRCodeReadListener(this);
        mScanPresenter = new ScanPresenter(this);
        updateTableStatus();
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        mScanPresenter.findTableFromQr(text);
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mQRCodeReaderView.getCameraManager().startPreview();
    }

    private void updateTableStatus() {
        boolean tableSet = BaseApp.getTableId().isSet();
        if (tableSet) {
            mTableFoundView.setVisibility(View.VISIBLE);
            mMainView.setVisibility(View.GONE);
        } else {
            mTableFoundView.setVisibility(View.GONE);
            mMainView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mQRCodeReaderView.getCameraManager().stopPreview();
    }

    @Override
    public void showNfc(String ndef) {
        mScanPresenter.findTableFromNfc(ndef);
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.table_not_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTable(int tableId) {
        updateTableStatus();
    }

    @OnClick(R.id.scan_status)
    void statusClick() {
        BaseApp.getTableId().delete();
        updateTableStatus();
    }
}
