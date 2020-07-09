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
import android.widget.RadioButton;
import android.widget.Toast;

public class Registration extends Activity {
EditText fnm,mnm,lnm,dob,ref,hse,pin,city,nat,ph,eml,pass;
RadioButton singl,married,m,f;
Button sub;
String res,gen,mstat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		
		
		fnm=(EditText)findViewById(R.id.editText1);
		mnm=(EditText)findViewById(R.id.editText2);
		lnm=(EditText)findViewById(R.id.editText3);
		dob=(EditText)findViewById(R.id.editText4);
		ref=(EditText)findViewById(R.id.editText5);
		hse=(EditText)findViewById(R.id.editText6);
		pin=(EditText)findViewById(R.id.editText7);
		city=(EditText)findViewById(R.id.editText8);
		nat=(EditText)findViewById(R.id.editText9);
		ph=(EditText)findViewById(R.id.editText10);
		eml=(EditText)findViewById(R.id.editText11);
		pass=(EditText)findViewById(R.id.editText12);
		singl=(RadioButton)findViewById(R.id.radio0);
		married=(RadioButton)findViewById(R.id.radio1);
		m=(RadioButton)findViewById(R.id.radio2);
		f=(RadioButton)findViewById(R.id.radio3);
		sub=(Button)findViewById(R.id.button1);
		
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				if(m.isChecked()==true)
				{
					gen="male";
					
				}
				else
				{
					gen="female";
					
				}
				
				if(singl.isChecked()==true)
				{
					mstat="Sin";
							
				}
				else
				{
					
					mstat="Mar";
				}
				
				
				if(ph.getText().toString().length()!=10)
				{
			ph.setText("");
			ph.setError("Invalid Phone");
				}
				if(!eml.getText().toString().matches("[a-zA-Z0-9._-]+@gmail+\\.+com+"))
				{
					eml.setText("");
					eml.setError("Invalid Email ID");	
				}
				if(pin.getText().toString().length()!=6)
				{
			pin.setText("");
			pin.setError("Invalid Phone");
				}
				
				
				if(pass.getText().toString().length()<4)
				{
			pass.setText("");
			pass.setError("Password Length Should be Minimum 4");
				}
				
				
				if(!fnm.getText().toString().equals("")&&!mnm.getText().toString().equals("")&&!lnm.getText().toString().equals("")&&!dob.getText().toString().equals("")&&!ref.getText().toString().equals("")&&!hse.getText().toString().equals("")&&!pin.getText().toString().equals("")&&!city.getText().toString().equals("")&&!nat.getText().toString().equals("")&&!ph.getText().toString().equals("")&&!eml.getText().toString().equals("")&&!pass.getText().toString().equals(""))
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
				
				String url = Login.url+"patientreg/android/";
				JSONObject jobj= new JSONObject();
		        try {
		        	//"": 23,
		        	
		        	jobj.put("fname",fnm.getText().toString());
					jobj.put("mname",mnm.getText().toString() );
					jobj.put("lname",lnm.getText().toString());
					jobj.put("gender",gen );
					jobj.put("phone",ph.getText().toString());
					jobj.put("house",hse.getText().toString() );
					jobj.put("pin",pin.getText().toString());
					jobj.put("city",city.getText().toString() );
					jobj.put("mstatus",mstat );
					jobj.put("reference",ref.getText().toString());
					jobj.put("dob",dob.getText().toString() );
					jobj.put("username",eml.getText().toString());
					jobj.put("pwd",pass.getText().toString() );
					jobj.put("nationality",nat.getText().toString() );
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
		        Intent i=new Intent(getApplicationContext(), Login.class);
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
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

}
