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

import in.eatfull.eatfull.Home;
import in.eatfull.eatfull.Models.oc_product;

/**
 * Created by steve on 26/6/16.
 */

public class Home_Task extends AsyncTask<Void,oc_product,Void> {

    public static ArrayList<oc_product> briyani_zone = new ArrayList<>();
    public static ArrayList<oc_product> chicken_zone = new ArrayList<>();
    public static ArrayList<oc_product> mutton_zone = new ArrayList<>();
    public static ArrayList<oc_product> seafood_zone = new ArrayList<>();
    public static ArrayList<oc_product> veg_zone = new ArrayList<>();
    public static ArrayList<oc_product> sweets_zone = new ArrayList<>();
    public static ArrayList<oc_product> oc_products_other = new ArrayList<>();
    public static ArrayList<oc_product> oc_products = new ArrayList<>();

    Context context;
    Activity activity;

    String JsonLink = "http://eatfull.in/Mobile_API/oc_product.json";

    public Home_Task(Context context) {
        this.context = context;
        this.activity = (Activity)context;
    }

    public Home_Task() {
        
    }


    @Override
    protected void onPreExecute() {

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

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line+"\n");
            }

//            closing connection
            httpURLConnection.disconnect();
            String Json_String = stringBuilder.toString().trim();

            JSONObject parentObject = new JSONObject(Json_String);
            JSONArray product = parentObject.getJSONArray("product");

            for(int i=0;i<product.length();i++)
            {
                JSONObject productJSONObject = product.getJSONObject(i);

                String name = null;
                String description = null;

                oc_product products = new oc_product();
                products.setProduct_id(productJSONObject.getInt("product_id"));
                products.setPrice(productJSONObject.getDouble("price"));
                products.setImage(productJSONObject.getString("image"));
                products.setModel(productJSONObject.getString("model"));
                products.setSort_order(productJSONObject.getInt("sort_order"));

                for(int j=0;j<product.length();j++){
                    if(productJSONObject.getInt("product_id") == oc_product_description_task.product_descriptions.get(j).getProduct_id()){
                        name = oc_product_description_task.product_descriptions.get(j).getName();
                         description = oc_product_description_task.product_descriptions.get(j).getDescription();
                        break;
                    }
                }

                products.setName(name);
                products.setDescription(description);
                String chic = "chicken";
                String mutt = "mutton";

                oc_products.add(products);

                if(productJSONObject.getString("model").equalsIgnoreCase("Biriyani zone")) {
                    if( name.toLowerCase().contains(chic)){
                        briyani_zone.add(products);
                    } else if(name.toLowerCase().contains(mutt)){
                        mutton_zone.add(products);
                    }else
                        veg_zone.add(products);
                }else if(productJSONObject.getString("model").equalsIgnoreCase("Chicken zone")) {
                    chicken_zone.add(products);
                }else if(productJSONObject.getString("model").equalsIgnoreCase("mutton zone")) {
                    mutton_zone.add(products);
                }else if(productJSONObject.getString("model").equalsIgnoreCase("Sea Food zone")) {
                    seafood_zone.add(products);
                }else if(productJSONObject.getString("model").equalsIgnoreCase("Veg zone")) {
                    veg_zone.add(products);
                } else if (productJSONObject.getString("model").equalsIgnoreCase("Sweets zone")){
                    sweets_zone.add(products);
                }else oc_products_other.add(products);
                publishProgress(products);
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
    protected void onProgressUpdate(oc_product... values) {
        Home.briyani_zone_adapter.notifyDataSetChanged();
        Home.chicken_zone_adapter.notifyDataSetChanged();
        Home.mutton_zone_adapter.notifyDataSetChanged();
        Home.seafood_zone_adapter.notifyDataSetChanged();
        Home.veg_zone_adapter.notifyDataSetChanged();
        Home.sweets_zone_adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}


