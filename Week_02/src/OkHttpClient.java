import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;


public class OkHttpClient {

    public static void main(String[] args) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("arg" , "参数");
            Request request = new Builder().url("http://localhost:8088/api/hello").get().build();
            Response response = okHttpClient.newCall(request).execute();
            if(response.code() == 200)
                System.out.println(response.body().string());
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}