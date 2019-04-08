package veronika.hella.obdapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {


   /* {
        "success": true,
            "message": {
        "msg": {
            "id": 3065,
                    "errorcode": "U2020",
                    "errordesc": "Audio Center Amp is not responding",
                    "partname": ""
        }
    }
    }*/


    RecyclerView recyclerView;
    static final String URL_DATA="https://jsonplaceholder.typicode.com/posts";

    List<RecyclerModel> itemList;
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        recyclerView=findViewById(R.id.contents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        loadData();


    }

    private void loadData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        /*StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //when response is successful
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("");


                    for(int i=0;i<array.length();i++){

                        JSONObject o=array.getJSONObject(i);
                        RecyclerModel item=new RecyclerModel(
                                o.getString("userId"),
                                o.getString("id"),
                                o.getString("title"),
                                o.getString("body")
                        );

                        itemList.add(item);

                    }

                    recyclerAdapter=new RecyclerAdapter(itemList);
                    recyclerView.setAdapter(recyclerAdapter);

                } catch (JSONException e) {

                    Log.e("ErrorMessage: ","Problem on the JSON object");

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //when error occurs
            }
        }
        );
*/


        JsonArrayRequest request=new JsonArrayRequest(
                URL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();


                        for(int i = 0; i < response.length()-1; i++) {
                            Log.e("ErrorMessage: ","array size ="+response.length());
                            try {
                                JSONObject o = response.getJSONObject(i);

                                RecyclerModel item=new RecyclerModel(
                                        o.getString("userId"),
                                        o.getString("id"),
                                        o.getString("title"),
                                        o.getString("body")
                                );

                                itemList.add(item);

                            }
                            catch(JSONException e) {
                             //   mEntries.add("Error: " + e.getLocalizedMessage());
                                Log.e("problemMessage: ",e.getLocalizedMessage());
                            }
                        }

                        Log.v("list_size: ","list size ="+itemList.size());

                        recyclerAdapter=new RecyclerAdapter(itemList);
                        recyclerView.setAdapter(recyclerAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        }
        );

         //used to execute the request
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



}
