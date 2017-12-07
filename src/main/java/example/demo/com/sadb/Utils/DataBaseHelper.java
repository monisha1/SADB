package example.demo.com.sadb.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import example.demo.com.sadb.Model.ItemListModel;
import example.demo.com.sadb.Model.SnippetModel;

/**
 * Created by Saad on 12/7/2017.
 */

public class DataBaseHelper  {

    private static final String TAG = DataBaseHelper.class.getSimpleName();

    private static DataBaseHelper mInstance = null;
    /** The context context. */
    private Context context;

    /** The database db. */
    private SQLiteDatabase db;

    public static DataBaseHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DataBaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DataBaseHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        if(db == null)
        this.db = openHelper.getWritableDatabase();
    }

    public void addContent(ItemListModel itemListModel) {

        try {
            Log.d(TAG, "Values Got " + itemListModel.getClass().getSimpleName()+ itemListModel.toString());

//        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            // values.put(Constants.DATABASE.PRODUCT_ID, vchannel.getProductId());
            values.put(Constants.DATABASE.TITLE, itemListModel.getSnippet().getTitle());
            values.put(Constants.DATABASE.DESCRIPTION, itemListModel.getSnippet().getDescription());
            values.put(Constants.DATABASE.PUBLIHED_AT, itemListModel.getSnippet().getPublishedAt());
            //     values.put(Constants.DATABASE.THUMBNAIL_URL,Utils.getPictureByteOfArray(Utils.StringToBitMap(vchannel.getThumbnailurl())));
          //  values.put(Constants.DATABASE.THUMBNAIL_URL,itemListModel.getThumbnailurl());



            db.insert(Constants.DATABASE.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        db.close();
    }


    public List<ItemListModel> getallData() {
    //    SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_VCHANNEL_QUERY, null);
        final List<ItemListModel> vcList = new ArrayList<>();

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
//                do {
                ItemListModel vchannel = new ItemListModel();
                // vchannel.setFromDatabase(true);
                SnippetModel snippetModel = new SnippetModel();
                snippetModel.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TITLE)));
                snippetModel.setDescription(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DESCRIPTION)));
                snippetModel.setPublishedAt(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PUBLIHED_AT)));
                //    vchannel.setThumbnailurl(Utils.BitMapToString(Utils.getBitmapFromByte(cursor.getBlob(cursor.getColumnIndex(Constants.DATABASE.THUMBNAIL_URL)))));

               // vchannel.setThumbnailurl(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.THUMBNAIL_URL)));
                vchannel.setSnippet(snippetModel);
                vcList.add(vchannel);
                // publishFlower(flower);

//                } while (cursor.moveToNext());
            }

        }
        return vcList;
    }

    public void deleteData() {
        db.execSQL(Constants.DATABASE.DELET_VCHANNEL_QUERY );
//        db.close();
    }
    public int numberOfRows() {
        // SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_VCHANNEL_QUERY, null);
        int nrows =  cursor.getCount();
        //  int numRows = DatabaseUtils.queryNumEntries(Constants.DATABASE.TABLE_NAME);
        Log.d("nrowsd",""+nrows);
        return nrows;
    }


    /**
     * The Class OpenHelper.
     */
    private static class OpenHelper extends SQLiteOpenHelper {

        /**
         * Instantiates a new open helper.
         *
         * @param context
         *            the context
         */
        OpenHelper(Context context) {
            super(context,Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
//			super(context, Environment.getExternalStorageDirectory()
//					+ File.separator + "/DataBase/" + File.separator
//					+ DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * On create called when DB is created.
         *
         * @param //the
         *            database
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
                Log.d(TAG, "oncreate "+ Constants.DATABASE.TABLE_NAME);
            } catch (SQLException ex) {
                Log.d(TAG, ex.getMessage());
            }

        }

        /**
         * On upgrade called when DB is upgraded.
         *
         * @param// the
         *            database
         * @param //the
         *            old version number
         * @param //the
         *            new version number
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Constants.DATABASE.DROP_QUERY);
            this.onCreate(db);
        }
    }

}
