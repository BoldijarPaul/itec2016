package com.itec.order.ui.fragments;


import android.graphics.PointF;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends NfcFragment implements QRCodeReaderView.OnQRCodeReadListener {

    @Bind(R.id.scan_qrcamera)
    QRCodeReaderView mQRCodeReaderView;

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
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPause() {
        super.onPause();
        mQRCodeReaderView.getCameraManager().stopPreview();
    }

    @Override
    public void showNfc(String ndef) {
        Toast.makeText(getContext(), ndef, Toast.LENGTH_LONG).show();
    }
}
