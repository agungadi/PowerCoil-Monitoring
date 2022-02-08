package org.uts.powercoil.model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPengkajian {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pengkajian mPengkajian;
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
    public Pengkajian getPengkajian() {
        return mPengkajian;
    }
    public void setPengkajian(Pengkajian pengkajian) {
        mPengkajian = pengkajian;
    }

}
