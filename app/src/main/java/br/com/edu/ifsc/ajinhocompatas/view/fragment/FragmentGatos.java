package br.com.edu.ifsc.ajinhocompatas.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ifsc.ajinhocompatas.R;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentGatos extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_desenvolvimento, container, false);
    }
}
