package com.example.dz1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import static java.lang.String.valueOf;

public class second_fragment extends Fragment {

    private  int ID;
    private int fragColor;

    public second_fragment(){}

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null){
            ID = savedInstanceState.getInt("ID");
            fragColor = savedInstanceState.getInt("Color");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_fragment, container, false);

    }
    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            TextView text = (TextView) view.findViewById((R.id.text_second));
            text.setText(valueOf(ID));
            text.setTextColor(fragColor);
        }

    }

    public  void setID(int id){
        this.ID = id;
    }

    public  void setColor(int hexColor) {this.fragColor = hexColor; }

    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("ID", ID);
        savedInstanceState.putInt("Color", fragColor);
    }
}


   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_fragment);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(MainActivity.EXTRA_VALUE);
        TextView v = findViewById(R.id.text3);
        v.setText(stringExtra);
        String hex = intent.getStringExtra((MainActivity.EXTRA_VALUE2));
        v.setTextColor(Color.parseColor(hex));
    }*/