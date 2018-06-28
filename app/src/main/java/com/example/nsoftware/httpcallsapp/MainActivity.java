package com.example.nsoftware.httpcallsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsoftware.httpcallsapp.Adapter.PhotoAdapter;
import com.example.nsoftware.httpcallsapp.Model.Photo;
import com.example.nsoftware.httpcallsapp.Network.HttpCall;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_photos;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo/findViews.
        lv_photos = findViewById(R.id.lv_photos);

        // todo/init.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(MainActivity.this)
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .showImageOnFail(R.drawable.information)
                        .build())
                .build();
        ImageLoader.getInstance().init(config);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        // todo/intent.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // NullPointerException.
            String name = bundle.getString(WelcomeActivity.EXTRA_KEY_NAME);
            int age = bundle.getInt(WelcomeActivity.EXTRA_KEY_AGE, 0);

//            // todo/styleable-toast library.
//            StyleableToast.makeText(this, "Hello World!",
//                    Toast.LENGTH_LONG, R.style.ToastStyle).show();

            // todo/custom toast.
            View layout = getLayoutInflater().inflate(R.layout.toast_view,
                    (ViewGroup) findViewById(R.id.toast_root));
            // todo/toast findViews.
            ImageView toast_iv = layout.findViewById(R.id.toast_iv);
            TextView toast_tv = layout.findViewById(R.id.toast_tv);

            // todo/toast initViews.
            toast_tv.setText("Welcome, " + name);
            toast_iv.setImageResource(R.drawable.ic_emotion);

            Toast toast = new Toast(getApplication());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                new NetworkCall()
                        .execute("https://jsonplaceholder.typicode.com/photos?albumId=1");
                break;
        }
        // return true.
        return super.onOptionsItemSelected(item);
    }

    public class NetworkCall extends AsyncTask<String, Void, List<Photo>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<Photo> doInBackground(String... urls) {
            List<Photo> photoList = new ArrayList<>();

            try {

                StringBuilder buffer = new StringBuilder();
                HttpCall.sendGetRequest(urls[0]);
                String[] response = HttpCall.readMultipleLineResponse();
                for (String line : response) {
                    buffer.append(line);
                }
                HttpCall.disconnect();

                // todo/parsing JSON.
                JSONArray rootJsonArray = new JSONArray(buffer.toString());
                Gson gson = new Gson();
                for (int i = 0; i < rootJsonArray.length(); i++) {
                    JSONObject jsonObject = rootJsonArray.getJSONObject(i);
                    photoList.add(gson.fromJson(jsonObject.toString(), Photo.class));
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return photoList;
        }

        @Override
        protected void onPostExecute(List<Photo> photoList) {
            super.onPostExecute(photoList);
            dialog.dismiss();
            lv_photos.setAdapter(new PhotoAdapter(MainActivity.this,
                    R.layout.item_photo, photoList));
        }
    }
}
