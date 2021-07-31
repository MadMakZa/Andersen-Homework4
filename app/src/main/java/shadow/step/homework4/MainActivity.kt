package shadow.step.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var buttonColorHour: Button
    private lateinit var buttonColorMin: Button
    private lateinit var buttonColorSec: Button
    private lateinit var buttonSetSize: FloatingActionButton
    private lateinit var clockView: TimeClockView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonColorHour = findViewById(R.id.btn_set_color_hour)
        buttonColorMin = findViewById(R.id.btn_set_color_minutes)
        buttonColorSec = findViewById(R.id.btn_set_color_seconds)
        buttonSetSize = findViewById(R.id.btn_set_size_hands)
        clockView = findViewById(R.id.clockView)

        init()
    }

    private fun init(){
        buttonColorSec.setOnClickListener {
            clockView.setColorHandSecondsTheme()
        }
        buttonColorMin.setOnClickListener {
            clockView.setColorHandMinutesTheme()
        }
        buttonColorHour.setOnClickListener {
            clockView.setColorHandHoursTheme()
        }
        buttonSetSize.setOnClickListener {
            clockView.setSizeHands()
        }
    }
}