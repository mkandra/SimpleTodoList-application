package com.codepath.apps.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {

	private static final String TAG = "TodoActivity";
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
        					android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        
        setupListEditListener();
    }
    
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
		if (resultCode == RESULT_OK) {
			String text = data.getExtras().getString("text");
			Log.e(TAG, "RESULT_OK");
			items.set(requestCode, text);
			itemsAdapter.notifyDataSetChanged();
			saveItems();
		}
	}



	private void setupListEditListener() {
    	lvItems.setOnItemClickListener(new OnItemClickListener() {
    		
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent EditFormIntent = new Intent("com.codepath.apps.simpletodo.EDITITEM");
				EditFormIntent.putExtra("text", items.get(position));
				EditFormIntent.putExtra("position", position);
				startActivityForResult(EditFormIntent, position);					
			}
    	});
    }
    
    public void addTodoItem(View v) {
    	EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
    	itemsAdapter.add(etNewItem.getText().toString());
    	etNewItem.setText("");
    	saveItems();
    }
    
    private void setupListViewListener() {
    	lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
    		public boolean onItemLongClick(AdapterView<?> parent,
    				View view, int position, long rowId) {
    			items.remove(position);
    			itemsAdapter.notifyDataSetChanged();
    			saveItems();
    			return true;
    		}
    	});
    }
    
    private void readItems() {
    	File filesDir = getFilesDir();
    	File todoFile = new File(filesDir, "todo.txt");
    	try {
    		items = new ArrayList<String>(FileUtils.readLines(todoFile));
    	} catch (IOException e) {
    		items = new ArrayList<String>();
    		e.printStackTrace();
    	}
    }
    
    private void saveItems() {
    	File filesDir = getFilesDir();
    	File todoFile = new File(filesDir, "todo.txt");
    	try {
    		FileUtils.writeLines(todoFile, items);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
}
