package com.example.dongho1.Activity.API;

import com.example.dongho1.Activity.Model.Customer;
import com.google.gson.annotations.SerializedName;

public class ChangeIn4Reponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Customer data;

    public ChangeIn4Reponse(boolean success, String code, String message, Customer data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }
}
