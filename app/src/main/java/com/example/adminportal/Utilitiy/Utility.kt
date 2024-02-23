package com.example.adminportal.Utilitiy

import okhttp3.ResponseBody
import org.json.JSONObject

object Utility {

    fun getErrorObject(responseBody: ResponseBody?): String  {
        if (responseBody == null) {
            return "something went wrong"
        }
        return JSONObject(responseBody.charStream().readText()).getString("message").ifBlank { "something went wrong" }
    }
}