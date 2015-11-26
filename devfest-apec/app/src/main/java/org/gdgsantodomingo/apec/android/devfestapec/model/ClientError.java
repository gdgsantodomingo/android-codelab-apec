package org.gdgsantodomingo.apec.android.devfestapec.model;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

public class ClientError {
    @SerializedName("message") private String                  message;
    @SerializedName("errors") private  List<ClientErrorDetail> errors;

    @NonNull public String getMessage() {
        return message;
    }

    @NonNull public List<ClientErrorDetail> getErrors() {
        return errors;
    }

    private ClientError(final String message) {
        this.message = message;
        this.errors = emptyList();
    }

    public static ClientError fromErrorBody(ResponseBody responseBody) {
        try {
            return new Gson().fromJson(responseBody.string(), ClientError.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClientError("Unknown Error");
    }
}
