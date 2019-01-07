package liangzs.com.myapplication.nanohttpd.protocols.http;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import liangzs.com.myapplication.nanohttpd.protocols.http.response.Response;

/**
 * @author liangzs
 * @Date 2019/1/7
 */
public class HttpController {
    private static final String TAG = "HttpController";

    /**
     * @return
     */
    @HttpMethod("/changeBg")
    public Response changeBg(Map<String, List<String>> param) {
        Log.i(TAG, "changeBg--->" + new Gson().toJson(param));
        return Response.newFixedLengthResponse("success");
    }
}
