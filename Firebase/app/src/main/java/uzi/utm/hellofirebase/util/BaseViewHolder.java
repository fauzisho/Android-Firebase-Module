package uzi.utm.hellofirebase.util;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T item, int position);
}