package com.base.baseapplication.data.network.models

data class Car (val id : Int, val imgUrl : String, val name : String, val make : String, val model : String,val year : String,
                var availability : Availability? = null)