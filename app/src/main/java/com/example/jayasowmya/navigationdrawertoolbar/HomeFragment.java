package com.example.jayasowmya.navigationdrawertoolbar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView1);
        String[] prgmName = getResources().getStringArray(R.array.prgmNameList);
        int[] images = {R.drawable.dd, R.drawable.enrique, R.drawable.illayraja, R.drawable.kkr, R.drawable.rajnikanth, R.drawable.rcb, R.drawable.rehman, R.drawable.srh};
        gridView.setAdapter(new GridAdapter(view.getContext(),prgmName,images)); // uses the view to get the context instead of getActivity().
        return view;
    }


}
