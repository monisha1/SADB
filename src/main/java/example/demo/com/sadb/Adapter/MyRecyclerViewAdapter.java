package example.demo.com.sadb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.demo.com.sadb.Model.ItemListModel;
import example.demo.com.sadb.R;

/**
 * Created by Saad on 12/8/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
private static final String TAG = "ProfileListAdapter";

private Context context;
// private List<ProfileItem> profileItemList = new ArrayList<>();
private List<ItemListModel> userprofileItemList = new ArrayList<>();

public MyRecyclerViewAdapter(Context context, List<ItemListModel>userprofileItemList /*List<ProfileItem> profileItemList*/) {
        this.context = context;
        this.userprofileItemList = userprofileItemList;
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvfullnameValue;


    public ViewHolder(View itemView, int viewType) {
        super(itemView);

        tvfullnameValue = itemView.findViewById(R.id.tv_name);
    }
}

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new MyRecyclerViewAdapter.ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        // ProfileItem profileItem = profileItemList.get(position);
        ItemListModel profileItem = userprofileItemList.get(position);


            holder.tvfullnameValue.setText(profileItem.getSnippet().getTitle());
        }


    @Override
    public int getItemCount() {
        return userprofileItemList.size();
    }
}


