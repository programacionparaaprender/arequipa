package luiscorrea.prueba.arequipa;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luiscorrea.prueba.arequipa.Config.Config;
import luiscorrea.prueba.arequipa.interfaz.ApiService;
import luiscorrea.prueba.arequipa.model.Publicaciones;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//import com.google.android.gms.maps.MapFragment;


public class MarkerDemoActivity extends FragmentActivity implements
    GoogleMap.OnMarkerClickListener,
    OnMapReadyCallback {

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_demo);

        SupportMapFragment mapFragment =
            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));




        // Add some markers to the map, and add a data object to each marker.
        mPerth = mMap.addMarker(new MarkerOptions()
            .position(PERTH)
            .title("Perth"));
        mPerth.setTag(0);

        mSydney = mMap.addMarker(new MarkerOptions()
            .position(SYDNEY)
            .title("Sydney"));
        mSydney.setTag(0);

        mBrisbane = mMap.addMarker(new MarkerOptions()
            .position(BRISBANE)
            .title("Brisbane"));
        mBrisbane.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

    }
    public void onMapReady2(GoogleMap map) {
        mMap = map;

        // Add some markers to the map, and add a data object to each marker.
        mPerth = mMap.addMarker(new MarkerOptions()
            .position(PERTH)
            .title("Perth"));
        mPerth.setTag(0);

        mSydney = mMap.addMarker(new MarkerOptions()
            .position(SYDNEY)
            .title("Sydney"));
        mSydney.setTag(0);

        mBrisbane = mMap.addMarker(new MarkerOptions()
            .position(BRISBANE)
            .title("Brisbane"));
        mBrisbane.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                marker.getTitle() +
                    " has been clicked " + clickCount + " times.",
                Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    /*
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
    */

}
