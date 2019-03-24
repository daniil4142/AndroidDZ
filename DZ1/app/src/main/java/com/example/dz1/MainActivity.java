package com.example.dz1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VALUE = "value";
    public static final String EXTRA_VALUE2 = "value2";
    int n = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> strings = new ArrayList<>();
        fillList(strings);


        RecyclerView recyclerView = findViewById(R.id.my_list);
        final GridLayoutManager layout = new  GridLayoutManager( this,3 );
        recyclerView.setLayoutManager(layout);
        final MyAdapter myAdapter = new MyAdapter(strings);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /* some logic with scrolling should be placed here */
            }
        });

        Button button = findViewById(R.id.my_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strings.add(strings.size() + "");
                n++;
                myAdapter.notifyItemInserted(myAdapter.getItemCount());
            }
        });
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {   onRestoreInstanceState(Bundle.EMPTY);
            layout.setSpanCount(3);

        }
        else layout.setSpanCount(4);

        /*recyclerView = (RecyclerView) onRetainNonConfigurationInstance();
        recyclerView = (RecyclerView) getLastNonConfigurationInstance();*/

    }


    void fillList(List<String> toFill) {
        /* any data from server etc */
        for (int i = 0; i < n; i++) {
            toFill.add(i + "");
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, OpenActivity.class);
                    intent.putExtra(EXTRA_VALUE, mTextView.getText());
                    int color = mTextView.getCurrentTextColor();
                    String hexColor = String.format("#%06X", (0xFFFFFF & color));
                    intent.putExtra(EXTRA_VALUE2, hexColor);
                    startActivity(intent);
                }
            });
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<String> mData;
        public MyAdapter(List<String> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            Log.d("TAG", "onCreateViewHolder for element type " + i);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            String str = mData.get(i);
            myViewHolder.mTextView.setText(str);
            if(i%2 == 0)  myViewHolder.mTextView.setTextColor(Color.RED);
            else     myViewHolder.mTextView.setTextColor(Color.BLUE);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
        protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", n);
            Log.d("TAG", "onSaveInstanceState");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        n = savedInstanceState.getInt("count");
        Log.d("TAG", "onRestoreInstanceState");
    }

}