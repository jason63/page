package com.jason63.page;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    RequestQueue rq ;
    String[] title, urlStr ;
    List<Item> itemList = new ArrayList<>() ;
    myAdapter mAdapter ;
    final int num =50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq = Volley.newRequestQueue(this) ;

        mAdapter = new myAdapter(this, itemList) ;
        ListView listView = findViewById(R.id.list) ;
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, detailActivity.class) ;
                intent.putExtra("url", itemList.get(position).getUrlStr()) ;
                startActivity(intent);
            }
        });
        doPost("https://getpocket.com/v3/get");
        rq.start();
    }
    private void doPost(final String url){
        StringRequest sr = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i("response----",s) ;
                        try {
                                title = matchPattern("\"resolved_title\":\"(.*?)\",",s) ;
                                urlStr = matchPattern("\"given_url\":\"(.*?)\",",s) ;
                                for(int i = 0; i< title.length;i++) {
                                title[i] = unicodeToString(title[i]).split("\"")[3] ;
                                urlStr[i] = urlStr[i].split("\"")[3].replaceAll("\\\\","")  ;
                                Item i1 = new Item(title[i], urlStr[i]) ;
                                itemList.add(i1) ;
                                }
                            mAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("consumer_key", "YOUR_CONSUMER_KEY");
                map.put("access_token", "YOUR_ACCESS_TOKEN");
                map.put("count",String.valueOf(num));
                map.put("detail_type", "simple");
                return map;
            }
        } ;
        rq.add(sr) ;
    }
    private String[] matchPattern(String p, String s){
        Pattern t = Pattern.compile(p) ;
        Matcher tt = t.matcher(s) ;
        int j = 0 ;
        String[] temp = new String[num] ;
        while(tt.find() && j<num){
            temp[j++] = tt.group() ;
            Log.i("match",p+", "+temp[j-1]) ;
        }
        return temp ;
    }

     public static String unicodeToString(String str) {
          Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
          Matcher matcher = pattern.matcher(str);
            char ch;
             while (matcher.find()) {
             String group = matcher.group(2);
             ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str; }
}
