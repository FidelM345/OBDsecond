package veronika.hella.obdapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import veronika.hella.obdapp.CarFailures;
import veronika.hella.obdapp.api.ApiHelper;
import veronika.hella.obdapp.R;

public class ResponseActivity extends AppCompatActivity {

    private static ArrayAdapter<String> adapter;

    private static ArrayList<String> itemList = new ArrayList<>();

    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        c = this.getApplicationContext();
       // initViewElements();

        String dtcs = getIntent().getExtras().getString("EXTRA_DTCS");

        if(dtcs==null)
            dtcs = "";
        if(!dtcs.equals("")) {

            //contains an array of error codes from the OBD device
            String[] dtcArray = dtcs.split("\\n");

            String language = getIntent().getExtras().getString("EXTRA_LANGUAGE");
            String vin = getIntent().getExtras().getString("EXTRA_VIN");

            Intent intent = new Intent(ResponseActivity.this, CarFailures.class);
            Bundle extras = new Bundle();

            extras.putStringArray("ERROR_CODES",dtcArray);
            intent.putExtras(extras);




/*
            ApiHelper api = new ApiHelper(c);
            api.getErrorCodeTranslation(dtcArray, vin , language);*/

        }else{
            //addListItem("No error codes received.");
            Toast.makeText(this,"No error codes received",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

   /* private void initViewElements() {
        if(getSupportActionBar()!=null)

            getSupportActionBar().setTitle(R.string.translation_result);

        ListView listView = (ListView) findViewById(R.id.output);

        adapter = new ResultAdapter(this, itemList);//assigning data to the Results Adapter class.


        listView.setAdapter(adapter);
        clearListView();
    }

    public static void addListItem(String value){

        itemList.add(value);
        adapter.notifyDataSetChanged();

    }

    private static void clearListView(){

        itemList.clear();
        adapter.notifyDataSetChanged();

    }

    public Context getC() {
        return c;
    }*/
}
