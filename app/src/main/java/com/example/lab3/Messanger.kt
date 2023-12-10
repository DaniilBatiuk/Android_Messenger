package com.example.lab3

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lab3.list.State
import com.example.lab3.list.StateAdapter
import java.time.LocalTime

class Messanger : AppCompatActivity() {
    var states = ArrayList<State>()
    var countriesList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messanger)



        setInitialData()
        countriesList = findViewById(R.id.list) as ListView
        val stateAdapter = StateAdapter(this, R.layout.list_item, states)

    }

    fun Send(view: View){
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        states.add(State("Anton", LocalTime.now(), editText.text.toString()))

    }




    private fun setInitialData() {

        states.add(State("Anton", LocalTime.parse("13:45"), "hi"))
        states.add(State("Lena", LocalTime.parse("13:47"), "hello"))
        states.add(State("Igor", LocalTime.parse("14:12"), "How are you?"))
        states.add(State("Ivan", LocalTime.parse("15:41"),"Ok"))
        states.add(State("Leva", LocalTime.parse("16:59"), "And you?"))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.menu_item_1 -> {
                val toast = Toast.makeText(
                    applicationContext,
                    "Clicked: settings", Toast.LENGTH_SHORT
                )
                toast.show()
                true
            }
            R.id.menu_item_3 -> {
                val toast = Toast.makeText(
                    applicationContext,
                    "Clicked: help", Toast.LENGTH_SHORT
                )
                toast.show()
                openAlterDialogYesNo()
                true
            }
            R.id.menu_item_2 -> {
                val toast = Toast.makeText(
                    applicationContext,
                    "Clicked: about", Toast.LENGTH_SHORT
                )
                toast.show()
//                showNotification()
//                showDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAlterDialogYesNo() {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> { val toast = Toast.makeText(
                        applicationContext,
                        "Clicked: Yes", Toast.LENGTH_SHORT
                    )
                        toast.show()}
                    DialogInterface.BUTTON_NEGATIVE -> { val toast = Toast.makeText(
                        applicationContext,
                        "Clicked: No", Toast.LENGTH_SHORT
                    )
                        toast.show()}
                    DialogInterface.BUTTON_NEUTRAL -> { val toast = Toast.makeText(
                        applicationContext,
                        "Clicked: Hz", Toast.LENGTH_SHORT
                    )
                        toast.show()}
                }
            }
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener)
            .setNeutralButton("hz", dialogClickListener).show()
    }
}