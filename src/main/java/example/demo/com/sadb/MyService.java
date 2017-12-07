package example.demo.com.sadb;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.demo.com.sadb.Model.ItemListModel;
import example.demo.com.sadb.Model.PlayListModel;
import example.demo.com.sadb.Utils.DataBaseHelper;
import example.demo.com.sadb.Utils.Utils;

/**
 * Created by Saad on 12/7/2017.
 */

public class MyService extends IntentService {
    private static final String TAG ="MyService";
    DataBaseHelper dataBaseHelper;

    public MyService(String name) {
        super(name);
    }
    public MyService() {
        super(TAG);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        dataBaseHelper =  DataBaseHelper.getInstance(getApplicationContext());

        getFeed();
        sendMessage();
    }

    //API Calls
    public void getFeed() {

        if (Utils.isNetworkAvailable(this)) {
            String url = "https://www.googleapis.com/youtube/v3/search?part=id,snippet&maxResults=20&channelId=UCCq1xDJMBRF61kiOgU90_kw&key=AIzaSyBRLPDbLkFnmUv13B-Hq9rmf0y7q8HOaVs";


            Log.d(TAG, "Feed Url : " + url);

// Creating volley request obj
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("MAinActivity", response.toString());


//                        ChannelList mchannel = null;
//                        RunInBackground rback;
                            PlayListModel playListModel = new Gson().fromJson(String.valueOf(response), PlayListModel.class);

                            List<ItemListModel> itemList = new ArrayList<>();
                            itemList.clear();
                            itemList.addAll(playListModel.getItems());
                            if (itemList.size() > 0) {
                                if (dataBaseHelper.numberOfRows() > 0) {
                                    dataBaseHelper.deleteData();
                                }
                            }

                            for(ItemListModel item : itemList){
                                dataBaseHelper.addContent(item);                      }
                        }


                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Volley Error", "Error: " + error.getMessage());
                    // hidePDialog();
                    //   pDialog.dismiss();
                }
            });

            RequestQueue requestueue = Volley.newRequestQueue(this);
            requestueue.add(jsonObjectRequest);
        }

    }

    public void sendMessage() {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
