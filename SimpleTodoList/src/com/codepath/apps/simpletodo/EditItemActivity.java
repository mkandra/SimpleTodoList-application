package com.codepath.apps.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		String text = getIntent().getStringExtra("text");
		
		EditText etItem = (EditText) findViewById(R.id.etItem);
		etItem.setText(text);
		etItem.setSelection(etItem.getText().length());
		
		Button btnSaveItem = (Button) findViewById(R.id.btnSaveItem);
		btnSaveItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent();
				EditText etItem = (EditText) findViewById(R.id.etItem);
				data.putExtra("text", etItem.getText().toString());
				setResult(RESULT_OK, data);
				finish();
			}
			
		});
		
		
	}
}
