package com.sirpum.trial.trialbluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityBlueTooth extends ActionBarActivity {
	
	final String myappName="MyBluetoothFriday";
	
	BroadcastReceiver mReceiver = null;

	final int REQUEST_ENABLE_BT = 999; 
	static final String BT_DEVICE_NAME = "RNBT-3323";
	static String BT_DEVICE_MAC_Address;
	static boolean BT_DEVICE_Connected = false;
	
	InputStream m_BTinputStream = null;
	OutputStream m_BTOutputStream = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_blue_tooth);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		/*
		 * added by arajmony 
		 *  
		 */
		
		final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter == null) {
			Log.w(myappName,"the device does not suport bluetooth");

		}else
			Log.i(myappName,"supports BLUETOOTH ##########");
		
		if (!bluetoothAdapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    	
               Log.i(myappName,"Device details : "+ device.getName() + "    ;    " + device.getAddress() +"\n");		 
               if(device.getName().trim().equals(BT_DEVICE_NAME))
            	   BT_DEVICE_MAC_Address = device.getAddress();

		    }
		}
		
		
		Log.i(myappName, "BT_DEVICE_NAME            : " + BT_DEVICE_NAME);
		Log.i(myappName, "BT_DEVICE_MAC_Address : " + BT_DEVICE_MAC_Address);
		
		
		// Create a BroadcastReceiver for ACTION_FOUND
		 mReceiver = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String action = intent.getAction();
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		        	
		            // Get the BluetoothDevice object from the Intent
		            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        	Log.i(myappName, "Device Discovered : " +device.getName() + "\t" + device.getAddress());
		        	
		            if(device.getName().equals(BT_DEVICE_NAME)){
		            	Log.i(myappName, "Cancelling Discovery before trying to connect ");
			            bluetoothAdapter.cancelDiscovery();
			            
			            if(! BT_DEVICE_Connected){
			            	Log.i(myappName, "Going to try connecting to the BT device from BroadcastReceiver.onReceive method");
			            	connectToKnownArduinoBT();// needed ??
			            }
		            }
		        }
		    }

		
		};
		// Register the BroadcastReceiver
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
		
		bluetoothAdapter.startDiscovery();

		 if(! BT_DEVICE_Connected){
         	Log.i(myappName, "Going to try connecting to the BT device from the outiside method");
         	int tryCount = 0;
         	
         	while(true){
         		tryCount ++;
         		
         		if(tryCount > 3){
         			Log.e(myappName, "could not connect to the BT device even after three times..");
         			break;
         		}
         		Log.i(myappName, "Trying to connect to BT device. Attempt # " + tryCount);
         		if(connectToKnownArduinoBT())
         			break;
         		
         		try {
					Thread.sleep(1000); //sleep for one second btw each try
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         	}
         }
	
		
		Log.i(myappName, "end of the onCreate()");
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}


//   4C:8D:79:EB:DF:5B

	private void connectToKnownMac(){
		BluetoothDevice macBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("4C:8D:79:EB:DF:5B");
		UUID macsUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
		
		try {
			Log.i(myappName, "going to create connection with device " + macBluetoothDevice);
			BluetoothSocket clientSocket = macBluetoothDevice.createRfcommSocketToServiceRecord(macsUUID);
			Log.i(myappName, clientSocket.getRemoteDevice().getName());
			clientSocket.getInputStream();
			clientSocket.connect();
			if(clientSocket.isConnected())
				Log.i(myappName, "connected with remote device ");
			else
				Log.w(myappName, "could not connect ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	

	
	//Name : RNBT-3323 
	//MAC address : 00:06:66:66:33:23
	//Pair code : 
	
	private boolean connectToKnownArduinoBT(){

		
		BluetoothDevice macBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(BT_DEVICE_MAC_Address);
		//This UUIS was got from http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html#createRfcommSocketToServiceRecord(java.util.UUID) 
		// The UUID is a general one for all Bluetooth Serial device :) .. thanks to Android documentation 
		UUID uuidforBTSerial = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
		
		try {
			Log.i(myappName, "going to create connection with BT device " + macBluetoothDevice);
			BluetoothSocket clientSocket = macBluetoothDevice.createRfcommSocketToServiceRecord(uuidforBTSerial);
			Log.i(myappName, clientSocket.getRemoteDevice().getName());
			m_BTinputStream = clientSocket.getInputStream();
			clientSocket.connect();
			if(clientSocket.isConnected()){
				BT_DEVICE_Connected = true;
				Log.i(myappName, "connected with remote BT device ");
				//start the listening thread
				Thread t = new Thread(new MyBluetoothListener());
				t.start();
				
				//write something once to the DT device
			    m_BTOutputStream = clientSocket.getOutputStream();
			    m_BTOutputStream.write("from the Android app".getBytes());
			}
			else
				Log.w(myappName, "could not connect to BT device ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(myappName, "Exception here : ", e);
			//e.printStackTrace();
			
		}
		
		return BT_DEVICE_Connected;
		
	}
	
	
	class MyBluetoothListener implements Runnable{

		@Override
		public void run() {
			
			try {
				Thread.sleep(1000); // sleep for some seconds ..1 sec ? for every run of the loop : save resources :) 
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(true){
				try {
					if(m_BTinputStream.available() > 0){
						byte[] byteAr = new byte[m_BTinputStream.available()];
						m_BTinputStream.read(byteAr);
						Log.i(myappName, "Data Received : " + new String(byteAr));
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(myappName, "inside the listening thread ",e);
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		
	}
	
	private void connectToTheDiscoveredDevice(BluetoothDevice device) {
		
		
		
		UUID uuid = UUID.randomUUID();
		try {
			Log.i(myappName, "going to create connection with device " + device);
			BluetoothSocket clientSocket = device.createRfcommSocketToServiceRecord(uuid);
			Log.i(myappName, clientSocket.getRemoteDevice().getName());
			clientSocket.getInputStream();
			clientSocket.connect();
			if(clientSocket.isConnected())
				Log.i(myappName, "connected with remote device ");
			else
				Log.w(myappName, "could not connect ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_blue_tooth, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_main_activity_blue_tooth, container,
					false);
			return rootView;
		}
	}

}