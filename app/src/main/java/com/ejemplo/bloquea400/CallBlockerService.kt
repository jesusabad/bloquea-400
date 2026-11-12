package com.ejemplo.bloquea400

import android.telecom.Call
import android.telecom.CallScreeningService

class CallBlockerService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {
        val handle = callDetails.handle
        val rawNumber = handle?.schemeSpecificPart ?: ""

        val digits = rawNumber.filter { it.isDigit() }
        val normalized = if (digits.startsWith("34") && digits.length > 9) {
            digits.removePrefix("34")
        } else {
            digits
        }

        val debeBloquear = normalized.startsWith("400")

        val response = CallResponse.Builder()
            .setDisallowCall(debeBloquear)
            .setRejectCall(debeBloquear)
            .setSkipCallLog(false)
            .setSkipNotification(false)
            .build()

        respondToCall(callDetails, response)
    }
}
