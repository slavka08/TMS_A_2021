package by.tms.stepsensorhw31

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_STEP_DETECTOR
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_NORMAL
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.tms.stepsensorhw31.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    private var stepCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(TYPE_STEP_DETECTOR)


        if (sensor == null){
            binding.tvSteps.text = getString(R.string.no_step_sensor)
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) -> {
                    sensorManager.registerListener(this, sensor, SENSOR_DELAY_NORMAL)
                }
                else -> {
                    requestPermissionLauncher.launch(
                        Manifest.permission.ACTIVITY_RECOGNITION
                    )
                }
            }
        } else {
            sensorManager.registerListener(this, sensor, SENSOR_DELAY_NORMAL)
        }
        setContentView(R.layout.activity_main)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        stepCount += (event?.values?.get(0) ?: 0f).toInt()
        binding.tvSteps.text = getString(R.string.step_count, stepCount)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                sensorManager.registerListener(this, sensor, SENSOR_DELAY_NORMAL)
            } else {
                binding.tvSteps.text = getString(R.string.need_motion_permission)
            }
        }
}