package programacionparaaprender.prueba.mapsjava2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;


    private Button btnLimpiar;

    private GoogleMap mMap;

    private LinkedList<LatLng> coordenadas = new LinkedList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                mMap.clear();
                coordenadas.clear();
            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng arg0)
            {
                android.util.Log.i("onMapClick", "Horray!");

                coordenadas.add(arg0);
                mMap.clear();

                List<PatternItem> pattern = null;
                int strokeColor = COLOR_BLACK_ARGB;
                int fillColor = COLOR_WHITE_ARGB;

                PolygonOptions pol =new PolygonOptions();
                pol.clickable(true);

                /*
                pol.strokePattern(pattern);
                pol.strokeWidth(POLYGON_STROKE_WIDTH_PX);
                pol.strokeColor(strokeColor);
                pol.fillColor(fillColor);
                */
                //pol.add(coordenadas);

                for(LatLng coor:coordenadas){
                    MarkerOptions marcaOpcion;
                    marcaOpcion = new MarkerOptions()
                        .position(coor);
                    //.title("Brisbane")
                    //marca.setPosition(arg0);
                    Marker marca = mMap.addMarker(marcaOpcion);
                    marca.setTag(0);
                    pol.add(coor);
                }
                Polygon polygon1 = mMap.addPolygon(pol);
// Store a data object with the polygon, used here to indicate an arbitrary type.
                polygon1.setTag("alpha");

                /*
                polygon1.setStrokePattern(pattern);
                polygon1.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
                polygon1.setStrokeColor(strokeColor);
                polygon1.setFillColor(fillColor);
                */
            }
        });

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
}
