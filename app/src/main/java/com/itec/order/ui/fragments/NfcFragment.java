package com.itec.order.ui.fragments;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.itec.order.ui.activities.HomeActivity;

import java.nio.charset.Charset;

/**
 * Created by bjz on 5/14/2016.
 */
public abstract class NfcFragment extends Fragment {
    public abstract void showNfc(String ndef);
    private NfcAdapter mNfcAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNfcAdapter=NfcAdapter.getDefaultAdapter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        enableForegroundDispatchWindow();
    }

    @Override
    public void onPause() {
        super.onPause();
        disableForegroundDispatchWindow();
    }

    public void enableForegroundDispatchWindow(){
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[]{};

        mNfcAdapter.enableForegroundDispatch(getActivity(), pendingIntent, intentFilters, null);
    }

    public void disableForegroundDispatchWindow(){
        mNfcAdapter.disableForegroundDispatch(getActivity());
    }

    public void onNewIntent(Intent intent){
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter
                    .EXTRA_NDEF_MESSAGES);

            if (parcelables != null && parcelables.length > 0) {
                readTextFromMessage((NdefMessage) parcelables[0]);
            } else {
                Toast.makeText(getContext(), "Error in parsing NFC", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Error in parsing NFC", Toast.LENGTH_SHORT).show();
        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {
        NdefRecord[] nDefRecords = ndefMessage.getRecords();

        if (nDefRecords != null && nDefRecords.length > 0) {
            NdefRecord ndefRecord = nDefRecords[0];
            String tagContent = new String(ndefRecord.getPayload(), Charset.forName("UTF8"));
            tagContent= tagContent.substring(3);
            showNfc(tagContent);
        } else {
            Toast.makeText(getContext(), "Nothing to read!", Toast.LENGTH_SHORT).show();
        }
    }



}
