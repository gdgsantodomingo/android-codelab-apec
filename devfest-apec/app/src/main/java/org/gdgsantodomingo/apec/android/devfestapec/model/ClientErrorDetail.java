package org.gdgsantodomingo.apec.android.devfestapec.model;

import com.google.gson.annotations.SerializedName;

public class ClientErrorDetail {
    @SerializedName("resource") private String resource;
    @SerializedName("field") private    String field;
    @SerializedName("code") private     String code;

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }
}
