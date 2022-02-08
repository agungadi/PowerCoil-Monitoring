package org.uts.powercoil.model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPower {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    Listrik mListrik;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Listrik getListrik() {
        return mListrik;
    }
    public void setListrik(Listrik listrik) {
        mListrik = listrik;
    }

}
