package veronika.hella.obdapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class CarFailures extends AppCompatActivity {

    EditText errors;
    Button submit;
    ProgressDialog progressDialog;
    List<CarFailuresModel>itemList;
    RecyclerView recyclerView;
    CarFailuresAdapter recyclerAdapter;

    String newString;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carfailures);

        errors=findViewById(R.id.error_code);
        submit=findViewById(R.id.clickme);

        recyclerView=findViewById(R.id.recycler_errors);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        itemList=new ArrayList<>();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemList.clear();
                data=errors.getText().toString().trim();

                final String list_codes []=data.split(",");
                newString = Arrays.toString(list_codes);
               // newString = newString.substring(1, newString.length()-1);

                 uploadDatata(list_codes);

            }
        });


         progressDialog=new ProgressDialog(CarFailures.this);

    }




    private static String mytoString(String[] theAray) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < theAray.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            String item = theAray[i];
            sb.append(item);
        }
        sb.append("");
        return sb.toString();
    }



    private void uploadDatata(String[] list_codes) {

        String url="http://mcarfix.demoscad.net/api/error-code";

      /*  HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("error_code", list_codes);
        RequestParams params = new RequestParams(paramMap);*/

        Toast.makeText(this,""+newString,Toast.LENGTH_LONG).show();

        String array_codes[]={"U2020,P0700,P0711,P0500,P0713"};

        String a="U2020,P0700,P0711,P0500,P0713";

        //getting the error codes from the OBD

       //String dtcs[] = getIntent().getExtras().getStringArray("ERROR_CODES");

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("error_code",a);
        RequestParams params = new RequestParams(paramMap);

        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.post(url, params, new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                progressDialog.setMessage("Loading.....");
                progressDialog.show();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressDialog.dismiss();
                Toast.makeText(CarFailures.this,"Fault Not Found",Toast.LENGTH_LONG).show();

            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                // called when response HTTP status is "200 OK"
                     progressDialog.dismiss();

                try {
                    JSONObject jsonObject  = new JSONObject(responseString);
                    boolean status = jsonObject.optBoolean("success", false);
                    JSONArray array = jsonObject.getJSONObject("message").getJSONArray("msg");
                    Log.v("Array_size",""+array.length());

                    if(status){
                        Toast.makeText(CarFailures.this,"Faults  Found",Toast.LENGTH_LONG).show();


                        for(int i=0;i<array.length();i++){

                            JSONObject o=array.getJSONObject(i);
                            CarFailuresModel item=new CarFailuresModel(
                                    o.getString("address_components"),
                                    o.getString("errorcode"),
                                    o.getString("errordesc"),
                                    o.getString("partname")
                            );

                            itemList.add(item);
                        }

                        Log.v("Object_No",""+itemList.size());

                        recyclerAdapter=new CarFailuresAdapter(itemList);
                        recyclerView.setAdapter(recyclerAdapter);


                    }else{

                        Toast.makeText(CarFailures.this,"Faults Not Found",Toast.LENGTH_LONG).show();

                    }

                  /*  {
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

                } catch (JSONException e) {
                   Log.e("APiError",e.getLocalizedMessage());
                }

            }
        });

    }



}
