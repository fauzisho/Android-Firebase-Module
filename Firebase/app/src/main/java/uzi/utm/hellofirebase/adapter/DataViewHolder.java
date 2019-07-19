package uzi.utm.hellofirebase.adapter;

import android.view.View;
import android.widget.TextView;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.model.Data;
import uzi.utm.hellofirebase.util.BaseViewHolder;

public class DataViewHolder extends BaseViewHolder<Data> {
    private final TextView fullname;
    private final TextView id;

    public DataViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        fullname = itemView.findViewById(R.id.fullname);
    }

    @Override
    public void bind(Data item) {
        fullname.setText(item.getName());
        id.setText(item.getId());
    }
}
