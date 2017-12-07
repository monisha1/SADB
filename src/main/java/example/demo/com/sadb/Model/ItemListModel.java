package example.demo.com.sadb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Saad on 12/7/2017.
 */

public class ItemListModel {
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;

    @SerializedName("snippet")
    @Expose
    private SnippetModel snippet;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public SnippetModel getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetModel snippet) {
        this.snippet = snippet;
    }
}
