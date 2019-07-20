package uzi.utm.hellofirebase.model;

import com.google.gson.annotations.SerializedName;

public class MsgNotification {

    @SerializedName("body")
    public String body;
    @SerializedName("title")
    public String title;

    public MsgNotification(String body, String title) {
        this.body = body;
        this.title = title;
    }
}
