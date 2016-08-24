package in.eatfull.eatfull.Task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import in.eatfull.eatfull.Models.oc_product_description;

/**
 * Created by steve on 26/6/16.
 */
//////////////////////////////
public class oc_product_description_task extends AsyncTask<Void, Void, Void> {
    public static ArrayList<oc_product_description> product_descriptions = new ArrayList<>();
    Home_Task home_task = new Home_Task();

    Context context;
    Activity activity;

    String JsonLink = "http://eatfull.in/Mobile_API/oc_product_description.json";

    public oc_product_description_task(Context context) {
        this.context = context;
        this.activity = (Activity)context;
    }

    public oc_product_description_task(int product_id, String name, String description) {

    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(JsonLink);

//            opening connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

//            getting data
            InputStream inputStream = httpURLConnection.getInputStream();

//            Buffering input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

//            closing connection
            httpURLConnection.disconnect();
            String Json_String = stringBuilder.toString().trim();

            JSONObject parentObject = new JSONObject(Json_String);
            JSONArray parentArray = parentObject.getJSONArray("product_description");

            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                oc_product_description product_description = new oc_product_description(finalObject.getInt("product_id"),
                        finalObject.getString("name"),finalObject.getString("description"));
                product_descriptions.add(product_description);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Void aVoid) {
            home_task.execute();
    }

}

