package com.prac.kotlinsandbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_location.*
import java.lang.RuntimeException
import java.util.jar.Manifest


class LocationActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 1001
    private val requestedPermissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION)
    var fusedLocationClient: FusedLocationProviderClient
            = LocationServices.getFusedLocationProviderClient(this)
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


    }

    /*
    위치 정보를 요청하고 처리하는 작업은 배터리 소모가 큰 작업
    따라서 화면에서 보이는 시점인 onResume에서 작업하고
    화면이 사라지는 시점인 onPause에서 콜백 리스터 해제
     */
    override fun onResume() {
        super.onResume()
        initLocation()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    private fun initLocation() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            location ->
            if(location == null) {
                /*
                기기 설정에서 위치를 비활성화 시킨 경우 -> 캐시까지 지워지기 때문에 이전에 얻은 마지막 위치도 구할 수 없음
                위치 권한을 한번 비활성화 시키고 다시 활성화 시켜도 앱에서 바로 위치 정보를 가져올 수 없음 -> 위치 업데이트 요청
                */
                Log.e("ERROR", "location get fail")
            }else{
                loc_lat_textView.text = location.latitude.toString()
                loc_lon_textView.text = location.longitude.toString()
            }
        }.addOnFailureListener {
            Log.e("ERROR", "location error is ${it.message}")
            it.printStackTrace()
        }

        // 위치 정보 요청
        locationRequest = LocationRequest.create()
        // 정확도와 인터벌 시간을 정해줌
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 60 * 1000
        }
        // 위치 데이터를 받을 콜백을 만들어 줌
        var locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                p0?.let {
                    for((i, location) in it.locations.withIndex()) {
                        loc_lat_textView.text = location.latitude.toString()
                        loc_lon_textView.text = location.longitude.toString()
                    }
                }
            }
        }
        // 위치 데이터 요청
        /*
        requestLocationUpdates는 interval마다 계속 현재 위치 데이터를 요청
        따라서 현재 위치를 주기적으로 체크하는 케이스에 알맞음
        ex. 런닝시 위치 정보를 기록하는 경우
         */
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }


}




