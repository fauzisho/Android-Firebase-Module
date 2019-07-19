package uzi.utm.hellofirebase.adapter;

import android.content.Context;
import android.view.View;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.model.Data;
import uzi.utm.hellofirebase.util.BaseRecyclerViewAdapter;

public class DataAdapter extends BaseRecyclerViewAdapter<Data, DataViewHolder> {
    private DataInterface listen;


    public DataAdapter(Context context, DataInterface listen) {
        super(context);
        this.listen = listen;
    }

    @Override
    protected DataViewHolder initViewHolder(View view) {
        return new DataViewHolder(view, listen);
    }

    @Override
    protected int setItemView(int viewType) {
        return R.layout.item_data;
    }
}
