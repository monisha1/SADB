package example.demo.com.sadb.Utils;

/**
 * Created by Saad on 12/7/2017.
 */

public class Constants {

    public static final class DATABASE {
        // wait, lets debug it// wait how you ll see log here show me // which log are u talking abt?
        public static final String DB_NAME = "YoutubeVChannel";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "vchannel";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        // I guess, this will work // run again
        public static final String GET_VCHANNEL_QUERY = "SELECT * FROM " + TABLE_NAME;
        public static final String DELET_VCHANNEL_QUERY = "DELETE  FROM " + TABLE_NAME;

        public static final String PRODUCT_ID = "productId";
        //  public static final String CATEGORY = "category";
        //public static final String PRICE = "price";
        public static final String DESCRIPTION = "description";
        public static final String TITLE = "Title";
        public static final String THUMBNAIL_URL = "thumbnail_url";
        public static final String PUBLIHED_AT = "datetime";
   /* String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_PH_NO + " TEXT" + ")";*/

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // now again, deleted// ok
// hurray but how? remember that syntax we changed hehe yes thats the reason// now lets uncomment those things ok
                // let me make it sure
                TITLE + " TEXT not null," +
                DESCRIPTION + " TEXT not null," +
                PUBLIHED_AT + " TEXT not null," +
                THUMBNAIL_URL + " TEXT)";
    }
}
