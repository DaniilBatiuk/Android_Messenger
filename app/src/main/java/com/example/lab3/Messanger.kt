package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.lab3.list.State
import com.example.lab3.list.StateAdapter
import java.time.LocalTime

class Messanger : AppCompatActivity() {
    var states = ArrayList<State>()
    var countriesList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_message)


        setInitialData()
        countriesList = findViewById(R.id.list) as ListView
        val stateAdapter = StateAdapter(this, R.layout.list_item, states)

    }
    private fun setInitialData() {

        states.add(State("Anton", LocalTime.parse("13:45"), "hi"))
        states.add(State("Lena", LocalTime.parse("13:47"), "hello"))
        states.add(State("Igor", LocalTime.parse("14:12"), "How are you?"))
        states.add(State("Ivan", LocalTime.parse("15:41"),"Ok"))
        states.add(State("Leva", LocalTime.parse("16:59"), "And you?"))
    }
}