package com.example.medcare;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	public static String url="http://192.168.43.149:8000/";
	
	EditText unm,pass;
	Button b1,b2;
	public String perm="okk";
	private String TAG = Login.class.getSimpleName();
	public static String ty,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        
        
        unm=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2); 
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!unm.getText().toString().equals("")&&!pass.getText().toString().equals(""))
				{
	new savejson().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Fill", 3).show();
				}
			}
		});
        
 b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(getApplicationContext(), Registration.class);
			startActivity(i);
			}
		});
        
        
        
    }


    
    private class savejson extends AsyncTask<Void, Void, Void>{
  		@Override
  		protected Void doInBackground(Void... params) {
  			// TODO Auto-generated method stub

  			String url = Login.url+"login/android/";
  			JSONObject jobj= new JSONObject();
  	        try {
  				jobj.put("username", unm.getText().toString());
  				jobj.put("pwd", pass.getText().toString());
  				String s= JsonHandler.Postjson(url, jobj);
  				JSONArray jdata =JsonHandler.Getjarray(s);
  				if(jdata!=null)
  				{
  					perm="error";
  					for (int i = 0; i < jdata.length(); i++) {
  						perm="ok";
  						JSONObject ob;
  						ob = jdata.getJSONObject(i);
  						uid=ob.getString("logid");
  						 ty=ob.getString("type");
  					    	if(ty.equals("patient"))
  							{
  					    		
  								Intent ii=new Intent(getApplicationContext(),MainActivity.class);
  								startActivity(ii);
  							}
  					    	
  					    
  					}
  					
  				}
  				else
  				{
  					perm="error";
  				}
  				Log.d("out", perm);
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			
  			return null; 
  		}
  		
  		@Override
  	      protected void onPostExecute(Void result) {
  	         super.onPostExecute(result);
  	         
  	         
  	         
  	         
  	         if(!perm.equals("error"))
  	         {

  	        	 Log.d("äuth", "ok");
  	        	 Toast.makeText(getApplicationContext(), "Login Successfull", 3).show();
  	        	 
  	        	 
  	         }
  	         else
  	         {
  	        	 Log.d("äuth", "error");
  	        	 Toast.makeText(getApplicationContext(), "Try Again", 3).show();
  	         }
  	      }
  	}
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
