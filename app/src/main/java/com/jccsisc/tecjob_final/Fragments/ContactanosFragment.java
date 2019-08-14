package com.jccsisc.tecjob_final.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jccsisc.tecjob_final.R;


public class ContactanosFragment extends Fragment {

    private TextView btn_llamarRebe;
    private TextView btn_llamarLeti;


    public ContactanosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactanos, container, false);

        btn_llamarLeti = view.findViewById(R.id.txt_cel_Leti);
//        final EditText mPhoneNoEt = (EditText) findViewById(R.id.et_phone_no);

        btn_llamarLeti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo ="322 151 1045";
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(getActivity(), "Enter a phone number", Toast.LENGTH_SHORT);
                }
            }
        });

        btn_llamarRebe = view.findViewById(R.id.txt_cel_Rebeca);
        btn_llamarRebe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo ="322 153 1933";
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(getActivity(), "Enter a phone number", Toast.LENGTH_SHORT);
                }
            }
        });


        return view;
    }

}
