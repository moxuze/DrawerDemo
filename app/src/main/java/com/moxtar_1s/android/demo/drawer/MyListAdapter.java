package com.moxtar_1s.android.demo.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyListAdapter extends ListAdapter<Integer, RecyclerView.ViewHolder> {
    public static final List<Integer> ICON_LIST;
    private boolean isPager;

    static {
        int[] arr = {
                R.drawable.ic_list_1,
                R.drawable.ic_list_2,
                R.drawable.ic_list_3,
                R.drawable.ic_list_4,
                R.drawable.ic_list_5,
                R.drawable.ic_list_6,
                R.drawable.ic_list_7,
                R.drawable.ic_list_8
        };
        ICON_LIST = new ArrayList<>();
        int len = arr.length;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ICON_LIST.add(arr[random.nextInt(len)]);
        }
    }

    private static DiffUtil.ItemCallback<Integer> diffCallback =
            new DiffUtil.ItemCallback<Integer>() {

        @Override
        public boolean areItemsTheSame(@NonNull Integer oldItem, @NonNull Integer newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Integer oldItem, @NonNull Integer newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MyListAdapter() {
        this(false);
    }

    public MyListAdapter(boolean isPager) {
        super(diffCallback);
        this.isPager = isPager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder, parent, false);
        if (isPager) {
            view.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            ViewGroup.LayoutParams params = view.findViewById(R.id.cellImageView).getLayoutParams();
            params.height = 0;
            params.width = 0;
        }
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ImageView) holder.itemView.findViewById(R.id.cellImageView))
                .setImageResource(getItem(position));
    }
}
