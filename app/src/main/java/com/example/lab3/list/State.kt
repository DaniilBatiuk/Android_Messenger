package com.example.lab3.list;

import java.time.LocalTime

class State(name: String, time: LocalTime, message: String) {
    var name: String
    var time: LocalTime
    var message: String

    fun getName(): String {
        return name
    }

    fun getTime(): LocalTime {
        return time
    }

    fun getMessage(): String {
        return message
    }

    fun setName(newName: String) {
        name = newName
    }

    fun setTime(newTime: LocalTime) {
        time = newTime
    }

    fun setMessage(newMessage: String) {
        message = newMessage
    }

    init {
        this.name = name
        this.time = time
        this.message = message
    }
}
