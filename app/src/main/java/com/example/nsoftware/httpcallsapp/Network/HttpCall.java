package com.example.nsoftware.httpcallsapp.Network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nsoftware on 19/06/18.
 */
public class HttpCall {
    private static final String TAG = "HttpCall";
    private static HttpsURLConnection urlConnection;

    // HTTP GET Request.
    public static HttpsURLConnection sendGetRequest(String requestURL)
            throws IOException {
        urlConnection = (HttpsURLConnection) new URL(requestURL).openConnection();
        urlConnection.setUseCaches(false);

        // Set GET Method.
        urlConnection.setRequestMethod("GET");

        // InputStream.
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);

        return urlConnection;
    }

    // HTTP POST Request.
    public static HttpsURLConnection sendPostRequest(
            String requestURL, Map<String, String> params) throws IOException {
        urlConnection = (HttpsURLConnection) new URL(requestURL).openConnection();
        urlConnection.setUseCaches(false);

        // Set GET Method.
        urlConnection.setRequestMethod("POST");

        // InputStream.
        urlConnection.setDoInput(true);

        StringBuilder requestParams = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            // OutputStream.
            urlConnection.setDoOutput(true);
            for (String key : params.keySet()) {
                String value = params.get(key);

                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=");
                requestParams.append(URLEncoder.encode(value, "UTF-8"));
                requestParams.append("&");
            }

            // POST params.
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            writer.write(requestParams.toString());
            writer.flush();

        }

        return urlConnection;
    }

    // Read Multi-Line from response.
    public static String[] readMultipleLineResponse()
            throws IOException {

        List<String> lineList = new ArrayList<>();
        if (urlConnection != null) {

            int responseCode = urlConnection.getResponseCode();
            Log.i(TAG, "Response Code :: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lineList.add(line);
                }

                bufferedReader.close();
            } else {
                throw new IOException("Request error");
            }
        } else {
            throw new IOException("Connection error.");
        }
        return lineList.toArray(new String[lineList.size()]);
    }

    // Close the urlConnection.
    public static void disconnect() {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }
}

/*
String urlParameters  = "param1=a&param2=b&param3=c";
byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
int    postDataLength = postData.length;
String request        = "http://example.com/index.php";
URL    url            = new URL( request );
HttpURLConnection conn= (HttpURLConnection) url.openConnection();
conn.setDoOutput( true );
conn.setInstanceFollowRedirects( false );
conn.setRequestMethod( "POST" );
conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
conn.setRequestProperty( "charset", "utf-8");
conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
conn.setUseCaches( false );
try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
   wr.write( postData );
}
* */
