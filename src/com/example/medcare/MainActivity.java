package com.example.medcare;
import android.os.Bundle;
import android.provider.Browser.BookmarkColumns;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button bk,dinfo,testres,pres,logout,b1,b2,b8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		bk=(Button)findViewById(R.id.button1);
		dinfo=(Button)findViewById(R.id.button2);
		testres=(Button)findViewById(R.id.button4);
		pres=(Button)findViewById(R.id.button3);
		b1=(Button)findViewById(R.id.button6);
		b2=(Button)findViewById(R.id.button7);
		b8=(Button)findViewById(R.id.button8);
		logout=(Button)findViewById(R.id.button5);
		b8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), Treatment.class);
				startActivity(i);
			}
		});
		
		bk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), Add_Booking.class);
				startActivity(i);
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), V_pat_his.class);
				startActivity(i);
			}
		});
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), V_notification.class);
				startActivity(i);
			}
		});
		
		
		
dinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(), ViewDoctor.class);
				startActivity(i);
			}
		});
testres.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i=new Intent(getApplicationContext(), View_testResult.class);
		startActivity(i);
	}
});
pres.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i=new Intent(getApplicationContext(), ViewPrescription.class);
		startActivity(i);
	}
});

logout.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), Login.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
