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

public class DemoPhoneGap extends Activity implements LocationListener {
  private TextView latituteField;
  private TextView longitudeField;
  private LocationManager locationManager;
  private String provider;

  
/** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    latituteField = (TextView) findViewById(R.id.TextView02);
    longitudeField = (TextView) findViewById(R.id.TextView04);

    // Get the location manager
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // Define the criteria how to select the locatioin provider -> use
    // default
    Criteria criteria = new Criteria();
    provider = locationManager.GPS_PROVIDER;//getBestProvider(criteria, true);
    Location location = null;//locationManager.getLastKnownLocation(provider);

    // Initialize the location fields
    if (location != null) {
      System.out.println("Provider " + provider + " has been selected.");
      onLocationChanged(location);
    } else {
      latituteField.setText("Location not available");
      longitudeField.setText("Location not available");
    }
  }

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
    float lat = (float) (location.getLatitude());
    float lng = (float) (location.getLongitude());
    latituteField.setText(String.valueOf(lat));
    longitudeField.setText(String.valueOf(lng));
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
      // This method is called when a provider is unable to fetch a location or if 
    // the provider has recently become available after a period of unavailability.
	  // TODO Auto-generated method stub
	  Toast.makeText(this, "Error with GPS " + provider,
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
}
