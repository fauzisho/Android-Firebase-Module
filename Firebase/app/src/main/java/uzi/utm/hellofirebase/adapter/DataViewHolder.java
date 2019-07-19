package uzi.utm.hellofirebase.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.model.Data;
import uzi.utm.hellofirebase.util.BaseViewHolder;

public class DataViewHolder extends BaseViewHolder<Data> {
    private final TextView fullname;
    private final TextView id;
    private final ImageView ivDelete;
    private final RelativeLayout rlData;
    private DataInterface listen;

    public DataViewHolder(View itemView, DataInterface listen) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        fullname = itemView.findViewById(R.id.fullname);
        ivDelete = itemView.findViewById(R.id.ivDelete);
        rlData = itemView.findViewById(R.id.rlData);

        this.listen = listen;
    }

    @Override
    public void bind(final Data item, final int position) {
        fullname.setText(item.getName());
        id.setText(item.getId());

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.onDeleteData(item);
            }
        });

        rlData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.onUpdateData(item,position);
            }
        });
    }
}
