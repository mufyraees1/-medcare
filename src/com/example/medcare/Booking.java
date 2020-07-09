package com.example.medcare;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Booking extends Activity {
EditText date,valdate;
TextView docnm;
Button book;
String res;
String docid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);
		
		
		 docid=getIntent().getStringExtra("drid");
		String nm=getIntent().getStringExtra("name");
		date=(EditText)findViewById(R.id.editText1);
		valdate=(EditText)findViewById(R.id.editText2);
		docnm=(TextView)findViewById(R.id.textView2);
		book=(Button)findViewById(R.id.button1);
		
		docnm.setText(nm);
		book.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!date.getText().toString().equals("")&&!valdate.getText().toString().equals(""))
				{
					new savejson().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Fill", 3).show();
				}
			}
		});
	}

	
	
	
	 private class savejson extends AsyncTask<Void, Void, Void>{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				String url = Login.url+"appoinment/android/";
				JSONObject jobj= new JSONObject();
		        try {
		        	//"": 23,
		        	
		        	jobj.put("drid",docid);
					jobj.put("pid",Login.uid );
					jobj.put("date",date.getText().toString());
//					jobj.put("validdate",valdate.getText().toString() );
					JsonHandler.Postjson(url, jobj);
					res="success";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					res="error";
				}
				
				return null; 
				
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);
		        if(res.equals("success"))
		        {
		        Toast.makeText(getApplicationContext(), "success", 3).show();	
		        Intent i=new Intent(getApplicationContext(), MainActivity.class);
		        startActivity(i);
		        
		        }
		        else
		        {
		        Toast.makeText(getApplicationContext(), "Error", 3).show();	
		        }
		        
		      }
			
		}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.booking, menu);
		return true;
	}

}
