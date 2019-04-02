package com.example.dz1;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class recycler_fragment extends Fragment {

private int number = 200;

    public  interface  GridListener{
        void itemClicked(int id, String hexColor);
    }

    private GridListener listener;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number");
        }
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);
        final ArrayList<String> strings = new ArrayList<>();
        fillList(strings);


        RecyclerView recyclerView = view.findViewById(R.id.my_list);
        final GridLayoutManager layout = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layout);
        final MyAdapter myAdapter = new MyAdapter(strings);
        recyclerView.setAdapter(myAdapter);
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // some logic with scrolling should be placed here
            }
        });*/

        Button button = view.findViewById(R.id.my_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strings.add(strings.size() + "");
                number++;
                myAdapter.notifyItemInserted(myAdapter.getItemCount());
            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layout.setSpanCount(3);
        } else layout.setSpanCount(4);


        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (GridListener)activity;
    }


    void fillList(List<String> toFill) {
        /* any data from server etc */
        for (int i = 0; i < number; i++) {
            toFill.add(i + "");
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;


        public MyViewHolder( View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<String> mData;

        public MyAdapter(List<String> data) {
            mData = data;
        }


        @Override
        public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            Log.d("TAG", "onCreateViewHolder for element type " + i);
            return new recycler_fragment.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
            String str = mData.get(i);
            myViewHolder.mTextView.setText(str);
            if (i % 2 == 0) myViewHolder.mTextView.setTextColor(Color.RED);
            else myViewHolder.mTextView.setTextColor(Color.BLUE);
            int color = myViewHolder.mTextView.getCurrentTextColor();
            final String hexColor = String.format("#%06X", (0xFFFFFF & color));
            myViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.itemClicked(i,hexColor);
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("number", number);
    }

}

