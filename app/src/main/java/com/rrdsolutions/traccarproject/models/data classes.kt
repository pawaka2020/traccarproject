package com.rrdsolutions.traccarproject.models

import java.util.*

data class User(
    var id: Int= 0,
    var name:String? = null,
    var email:String? = null,
    var readonly:Boolean = false,
    var administrator:Boolean= false,
    var map:String? = null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var zoom:Int= 0,
    var password: String? = null,
    var twelveHourFormat:Boolean= false,
    var coordinateFormat:String? = null,
    var disabled:Boolean= false,
    var expirationTime:String? = null,
    var deviceLimit: Int= 0,
    var userLimit:Int= 0,
    var deviceReadonly: Boolean= false,
    var limitCommands:Boolean= false,
    var poiLayer:String? = null,
    var token:String? = null,
    var attributes: Attribute
)

data class NotificationType(
    var type:String
)

data class Device(
    var id: Int = 0,
    var name: String? = null,
    var uniqueId: String? = null,
    var status: String? = null,
    var lastUpdate: Date? = null,
    val positionId: Int = 0,
    val groupId: Int = 0,
    val phone: String? = null,
    val model: String? = null,
    val contact: String? = null,
    val category: String? = null,
    val geofenceIds: List<Int>,
    val attributes:Attribute
){
//    override fun toString() =
//        "\n$id\n$name\n$uniqueId\n$status\n$lastUpdate\n$positionId\n$groupId\n$phone\n$model\n$contact\n$category\n$geofenceIds\n$attributes"

    override fun toString()
    = "\nid: $id\nname: $name\nuniqueId: $uniqueId\n"
}

data class Attribute(
    var id:Int = 0,
    var description: String = "",
    var attribute:String = "",
    var expression: String = "",
    var type:String = ""
)

data class Event(
    var id:Int,
    var type:String,
    var serverTime:String,
    var deviceId:Int,
    var positionId:Int,
    var geofenceId:Int,
    var maintenanceId:Int,
    var attributes:Attribute
)

data class Statistics(
    var captureTime:String,
    var activeUsers:Int,
    var activeDevices:Int,
    var requests:Int,
    var messagesReceived: Int,
    var messagesStored:Int
)

