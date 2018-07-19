package edu.rice.pliny.apitrans.examples;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class HTTP {
    String url = "http://www.google.com";

    @Test
    public void read_text() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(this.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String text =  result.toString();
        assertEquals(true, text.contains("Google"));
    }

    @Test
    public void read_text2() throws Exception {
        OkHttpClient client = new OkHttpClient();

        Builder builder = new Builder();
        builder.url(url);
        okhttp3.Request request = builder.build();
        Call call = client.newCall(request);
        okhttp3.Response response = call.execute();
        okhttp3.ResponseBody rbody = response.body();
        String text = rbody.string();
        assertEquals(true, text.contains("Google"));
    }

}
