package com.example.dz1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpenActivity   extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_list);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(MainActivity.EXTRA_VALUE);
        TextView v = findViewById(R.id.text3);
        v.setText(stringExtra);
        String hex = intent.getStringExtra((MainActivity.EXTRA_VALUE2));
        v.setTextColor(Color.parseColor(hex));
    }
}
