package com.example.lab3.list;

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lab3.R

class StateAdapter(context: Context?, private val layout: Int, private val states: List<State>) :
    ArrayAdapter<State?>(context!!, layout, states) {

    private val TAG = "StateAdapter"
    private val inflater: LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, parent, false)
        val messageView = view.findViewById<TextView>(R.id.message)
        val nameView = view.findViewById<TextView>(R.id.name)
        val timeView = view.findViewById<TextView>(R.id.time)
        val state = states[position]

        messageView.text = state.getMessage()
        nameView.text = state.getName()
        timeView.text = state.getTime().toString()

        Log.d(TAG, "Item: " + position)
        return view
    }

    init {
        inflater = LayoutInflater.from(context)
    }

}
