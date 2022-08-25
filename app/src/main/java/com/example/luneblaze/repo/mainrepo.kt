package com.example.luneblaze.repo

import com.example.luneblaze.datamodel.Data
import com.example.luneblaze.datamodel.blazeModel
import com.example.luneblaze.network.luneNetwork

class mainrepo() {

    suspend fun getapiresp():blazeModel{
        return luneNetwork.getapiservice().getSBlazeApi("291",
            "article", "306", "2", "user")
    }
}