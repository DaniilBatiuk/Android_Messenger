package com.example.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ChatFrangment : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?    ): View?
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        val v = inflater.inflate(R.layout.fragment_chat, container, false)
//        val b = v.findViewById<View>(R.id.button2) as Button
        return v
    }

}