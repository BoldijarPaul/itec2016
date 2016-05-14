package com.itec.order.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment {
    private final static String IMAGEID = "ImageId";
    private final static String DESCRIPTION = "description";
    @Bind(R.id.tutorial_image)
    ImageView mTutorialImage;
    @Bind(R.id.tutorial_image_description)
    TextView mDescription;

    public static TutorialFragment newInstance(int imageId, String text) {
        Bundle args = new Bundle();

        args.putInt(IMAGEID, imageId);
        args.putString(DESCRIPTION, text);
        TutorialFragment fragment = new TutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        int idPhoto = getArguments().getInt(IMAGEID);
        String text = getArguments().getString(DESCRIPTION);
        mDescription.setText(text);
        mTutorialImage.setImageResource(idPhoto);

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false);
    }

}
