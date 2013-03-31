package com.Foo.DemoPhoneGap;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
 

public class DemoPhoneGap extends Activity implements LocationListener {
  private TextView latituteField;
  private TextView longitudeField;
  private LocationManager locationManager;
  private String provider;
  Button button;
  int lat2;
  int long2;
  int flag=0;
  float lat;
  float lng;
  
/** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    latituteField = (TextView) findViewById(R.id.TextView02);
    longitudeField = (TextView) findViewById(R.id.TextView04);
    addListenerOnButton();

    // Get the location manager
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // Define the criteria how to select the locatioin provider -> use
    // default
    Criteria criteria = new Criteria();
    provider = locationManager.GPS_PROVIDER;//getBestProvider(criteria, true);
    Location location = null;
    locationManager.getLastKnownLocation(provider);

    // Initialize the location fields
   if (location != null) {
      System.out.println("Provider " + provider + " has been selected.");
      onLocationChanged(location);
    } else {
      latituteField.setText("waiting...");
      longitudeField.setText("waiting...");
    }
  }
//

//
  /* Request updates at startup */
  @Override
  protected void onResume() {
    super.onResume();
    locationManager.requestLocationUpdates(provider, 400, 1, this);
  }

  /* Remove the locationlistener updates when Activity is paused */
  @Override
  protected void onPause() {
    super.onPause();
    locationManager.removeUpdates(this);
  }

  @Override
  public void onLocationChanged(Location location) {
     lat = (float) (location.getLatitude());
     lng = (float) (location.getLongitude());
    latituteField.setText(String.valueOf(lat));
    longitudeField.setText(String.valueOf(lng));
    if(flag==1) 
     { 
          if(lat2==(int) lat && long2 == (int) lng){
          Toast.makeText(this,"Location reached",
		        Toast.LENGTH_SHORT).show();}
		  }
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
      // This method is called when a provider is unable to fetch a location or if 
    // the provider has recently become available after a period of unavailability.
	  // TODO Auto-generated method stub
	  Toast.makeText(this, "Error with  " + provider,
		        Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void onProviderEnabled(String provider) {
    Toast.makeText(this, provider + "  Enabled.",
        Toast.LENGTH_SHORT).show();

  }
  @Override
  public void onProviderDisabled(String provider) {
    Toast.makeText(this, provider + "  Disabled.",
        Toast.LENGTH_SHORT).show();
  }
  
  	public void addListenerOnButton() {
 
		button = (Button) findViewById(R.id.button1);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			        lat2 = (int) lat;
                    long2 = (int) lng;
                    flag=1;
			}
 
		});
 
	}
}
