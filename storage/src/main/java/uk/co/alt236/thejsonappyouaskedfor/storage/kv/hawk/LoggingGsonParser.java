package uk.co.alt236.thejsonappyouaskedfor.storage.kv.hawk;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.hawk.Parser;

import java.lang.reflect.Type;

/*package*/ final class LoggingGsonParser implements Parser {
    private static final String TAG = "KV-HAWK-CONVERSION";
    private final Gson gson;

    public LoggingGsonParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T fromJson(String content, Type type) throws JsonSyntaxException {
        try {
            if (TextUtils.isEmpty(content)) {
                return null;
            }
            return gson.fromJson(content, type);
        } catch (final Exception e) {
            Log.e(TAG, "--------------------------------------------------");
            Log.e(TAG, "HAWK GSON CONVERSION ERRROR");
            Log.e(TAG, "Type     : " + type);
            Log.e(TAG, "Exception: " + e.getClass().getSimpleName());
            Log.e(TAG, "Message  : " + e.getMessage());
            Log.e(TAG, "--------------------------------------------------");

            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String toJson(Object body) {
        return gson.toJson(body);
    }

}