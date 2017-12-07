package example.demo.com.sadb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.demo.com.sadb.Adapter.MyRecyclerViewAdapter;
import example.demo.com.sadb.Model.ItemListModel;
import example.demo.com.sadb.Utils.DataBaseHelper;

public class SecondActivity extends AppCompatActivity {

    RecyclerView rvItem;
    LinearLayoutManager linearLayoutManager;
    MyRecyclerViewAdapter adapter;
    List<ItemListModel> list = new ArrayList<ItemListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rvItem = findViewById(R.id. rvItem);
         initView();
    }

    private void initView() {

        DataBaseHelper dataBaseHelper =  DataBaseHelper.getInstance(this);
        list  = dataBaseHelper.getallData();

        rvItem.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(linearLayoutManager);
        adapter = new MyRecyclerViewAdapter(this,list);
        rvItem.setAdapter(adapter);

    }


}
