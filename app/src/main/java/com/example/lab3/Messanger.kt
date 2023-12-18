package com.example.lab3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.lab3.list.State
import com.example.lab3.list.StateAdapter
import com.example.lab3.services.MsgService
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream
import java.time.LocalTime
import android.content.SharedPreferences
class Messanger : AppCompatActivity() {
    var states = ArrayList<State>()
    var countriesList: ListView? = null
    var br: BroadcastReceiver? = null
    SharedPreferences sPref = null;

    private val LOG_TAG = "Messanger"

    val BROADCAST_ACTION = "com.itstep.messanger.servicebackbroadcastmessage"
    val PARAM_TASK          = "task"
    val PARAM_USERS_LIST    = "users_list"
    val PARAM_TEXT_MSG      = "textMsg"
    val PARAM_USER_NICK      = "userNick"

    val TASK_MSH = 1
    val TASK_USERS = 2

    var bWorkService: Boolean = true
    var etText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messanger)
        var button = findViewById(R.id.button3) as Button
        etText = findViewById<View>(R.id.editTextTextMultiLine) as EditText
        SetFont(button)
        CreateImage()
        setInitialData()
        countriesList = findViewById(R.id.list) as ListView
        val stateAdapter = StateAdapter(this, R.layout.list_item, states)

        br = object : BroadcastReceiver() {
            // действия при получении сообщений
            override fun onReceive(context: Context, intent: Intent) {
//                val PARAM_TASK          = "task"
//                val PARAM_STATUS        = "status"
//                val PARAM_TEXT_MSG      = "textMsg"

                val task = intent.getIntExtra(PARAM_TASK, 0)

                if (task == TASK_MSH) {
                    val msg = intent.getStringExtra(PARAM_TEXT_MSG)
                    val user = intent.getStringExtra(PARAM_USER_NICK)

                    Log.i(LOG_TAG, "$user - $msg")


                } else
                    if (task == TASK_USERS) {
                        val usersList = intent.getStringExtra(PARAM_USERS_LIST)

                        Log.i(LOG_TAG, usersList!!)
                    }
            }
        }
    }
    override fun onStart() {
        super.onStart()

        LoadPrefs()
        // create filter for BroadcastReceiver
        val intFilt = IntentFilter(BROADCAST_ACTION)
        // register BroadcastReceiver
        registerReceiver(br, intFilt)

        Log.i(LOG_TAG, "onStart")
    }
    override fun onStop() {
        super.onStop()

        Log.i(LOG_TAG, "onStop")
        unregisterReceiver(br)

        SavePrefs();
    }

    fun buttonClick(view: View)
    {
        intent = Intent(this, MsgService::class.java)
        intent.putExtra("time", 7)
        startService(intent)
    }

    fun buttonClickStop(view: View)
    {
        intent = Intent(this, MsgService::class.java)
        stopService(intent)
    }

    private fun SavePrefs() {
//        val myPreferences = getSharedPreferences("com.itstep.messanger_preferences", Context.MODE_PRIVATE);
        val myPreferences = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE);
        val myEditor = myPreferences.edit()
        myEditor.putString("NAME", "Alice");
        myEditor.putInt("AGE", 25);
        myEditor.putBoolean("SINGLE?", true);
        myEditor.commit();

    }
    fun Send(view: View){
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        states.add(State("Anton", LocalTime.now(), editText.text.toString()))

    }

    private fun LoadPrefs() {
//        val myPreferences = getSharedPreferences("com.itstep.messanger_preferences", Context.MODE_PRIVATE);
        val myPreferences = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE);

        val name = myPreferences.getString("NAME", "unknown")
        val age = myPreferences.getInt("AGE", 0)
        val isSingle = myPreferences.getBoolean("SINGLE?", false)

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


    private fun CreateImage() {
        val imageView = findViewById<View>(R.id.image) as ImageView
        val filename = "photo.jpg"
        var inputStream: InputStream? = null
        try {
            inputStream = applicationContext.assets.open(filename)
            if (inputStream != null) {
                try {
                    val drawable = Drawable.createFromStream(inputStream, null)
                    imageView.setImageDrawable(drawable)
                    imageView.scaleType = ImageView.ScaleType.CENTER
                } catch (e1: Exception) {
                    Log.e("Messanger", e1.message!!)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    private fun SetFont(view: Button) {
        val myCustomFontBold = Typeface.createFromAsset(assets, "font/fontawesome_webfont.ttf")
        view.typeface = myCustomFontBold
    }

    fun writeText() {
        if (!etText!!.getText().toString().isEmpty()) {
            val file = File(this.filesDir, "text")
            if (!file.exists()) {
                file.mkdir()
            }
            //etText.getText().toString().getBytes()
            try {
                val gpxfile = File(file, "file.txt")
                val writer = FileWriter(gpxfile)
                writer.append(etText!!.getText().toString())
                writer.flush()
                writer.close()
                //output.setText(readFile());
                Toast.makeText(this, "Saved your text", Toast.LENGTH_LONG).show()
            } catch (e: java.lang.Exception) {
            }
        }
    }

    private fun readFile(): String? {
        val fileEvents = File(this.filesDir.toString() + "/text/file.txt")
        val text = java.lang.StringBuilder()
        try {
            val br = BufferedReader(FileReader(fileEvents))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            br.close()
        } catch (e: IOException) {
        }
        return text.toString()
    }


    fun testWriteFile() {
        val internalStorageDir = filesDir
        val alice = File(internalStorageDir, "alice001.csv")

        // Create file output stream
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(alice)
            val s = "Alice,25,1"
            fos.write(s.toByteArray())
            // Close the file output stream
            fos.close()
        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        // Write a line to the file
    }


    fun Read(view: View)
    {
        etText!!.setText(readFile());
    }

    fun Write(view: View)
    {
        writeText()
        testWriteFile()
    }
    val LOGIN = "login"
    val PASWORD = "password"



    fun loadTextPrefs() {
        sPref = getPreferences(MODE_PRIVATE)
        val loginText: String = sPref!!.getString(LOGIN, "")
        val paswordText: String = sPref!!.getString(PASWORD, "")
        etText!!.setText(loginText)
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show()
    }
}