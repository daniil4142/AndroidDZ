package com.example.dz1;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;


public class MainActivity extends Activity implements recycler_fragment.GridListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void itemClicked(int id,String hexColor){
        second_fragment frag = new second_fragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        frag.setID(id);
        frag.setColor(Color.parseColor(hexColor));
        ft.replace(R.id.container, frag);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

}