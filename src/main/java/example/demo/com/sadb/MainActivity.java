package example.demo.com.sadb;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import example.demo.com.sadb.Utils.Utils;

public class MainActivity extends AppCompatActivity {
    private static final String  TAG = "MainActivity";

    List<ItemListModel> itemList =  new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  getFeed();
        accessPermissions(this);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
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

                                itemList.addAll(playListModel.getItems());
                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data

                               // madapter.notifyDataSetChanged();


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

    // Our handler for received Intents. This will be called whenever an Intent
    // with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.d("receiver", "Got message: " + message);
            initview();
        }
    };

    private void initview() {
        Intent in = new Intent(this, SecondActivity.class);
        startActivity(in);
    }


    public static void accessPermissions(Activity activity){
        int permissionCheck_getAccounts = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.GET_ACCOUNTS);
        int permissionCheck_callPhone = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE);
        int permissionCheck_lockwake = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WAKE_LOCK);
        int permissionCheck_internet = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.INTERNET);
        int permissionCheck_Access_internet = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_NETWORK_STATE);
        int permissionCheck_Access_wifi = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck_External_storage = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck_cam = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA);

        if(permissionCheck_internet != PackageManager.PERMISSION_GRANTED||permissionCheck_Access_internet != PackageManager.PERMISSION_GRANTED||permissionCheck_External_storage != PackageManager.PERMISSION_GRANTED|| ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED||permissionCheck_cam != PackageManager.PERMISSION_GRANTED||permissionCheck_callPhone!= PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.INTERNET) && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)&& ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
               /* Fragment homeFragment = null;
                if(GlobalFunctions.getUserType(mainContext).equals(GlobalVariables.USER_TYPE.VENDOR)){homeFragment = new VendorQuotationFragment();}
                else{homeFragment = new HomeFragment();}
                mainActivityFM.beginTransaction().replace(R.id.container, homeFragment, "").commitAllowingStateLoss();*/
            } else {
            }
        }
        /*gpsTracker.getLocation();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //if (requestCode==GlobalVariables.PERMISSIONS_REQUEST_PRIMARY) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /*Fragment homeFragment = null;
                if(GlobalFunctions.getUserType(mainContext).equals(GlobalVariables.USER_TYPE.VENDOR)){homeFragment = new VendorQuotationFragment();}
                else{homeFragment = new HomeFragment();}
                mainActivityFM.beginTransaction().replace(R.id.container, homeFragment, "").commitAllowingStateLoss();*/
            }
      //  }
    }

    @Override
    protected void onPause() {
        // Unregister since the activity is paused.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mMessageReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        //https://gist.github.com/Antarix/8131277
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("custom-event-name"));
        super.onResume();
    }
    }

