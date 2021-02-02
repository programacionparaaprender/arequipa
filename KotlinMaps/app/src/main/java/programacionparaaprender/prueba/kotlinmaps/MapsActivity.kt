package programacionparaaprender.prueba.kotlinmaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var btnLimpiar: Button

    private lateinit var  coordenadas: LinkedList<LatLng>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnLimpiar!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v:View) {
                //startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                mMap.clear()
                coordenadas.clear()
            }
        });
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        coordenadas = LinkedList<LatLng>()

        mMap!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng1: LatLng) {
                /*
                val marcaOpcion1:MarkerOptions
                marcaOpcion1 = MarkerOptions()
                    .position(latLng1)
                val marca = mMap.addMarker(marcaOpcion1)
                marca.setTag(0)

                return
                */
                coordenadas.add(latLng1)
                mMap.clear()
                val pol = PolygonOptions()
                pol.clickable(true)

                //pol.add(coordenadas);
                /*
                for (coor in coordenadas)
                {
                    val marcaOpcion:MarkerOptions
                    marcaOpcion = MarkerOptions()
                        .position(coor)
                    val marca = mMap.addMarker(marcaOpcion)
                    marca.setTag(0)
                    pol.add(coor)
                }*/
                val positionIterator = coordenadas.iterator()
                while (positionIterator.hasNext()) {
                    val coor: LatLng
                    coor = positionIterator.next()
                    val marcaOpcion:MarkerOptions
                    marcaOpcion = MarkerOptions()
                        .position(coor)
                    val marca = mMap.addMarker(marcaOpcion)
                    marca.setTag(0)
                    pol.add(coor)
                }
                val polygon1 = mMap.addPolygon(pol)
                polygon1.setTag("alpha")


            }
        })

    }
}
