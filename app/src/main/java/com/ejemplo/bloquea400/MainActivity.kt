package com.ejemplo.bloquea400

import android.app.Activity
import android.app.role.RoleManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val REQ_ROLE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val texto = TextView(this).apply {
            text = "Esta app bloquea llamadas entrantes que empiecen por 400."
        }

        val boton = Button(this).apply {
            text = "Activar bloqueo de llamadas"
            setOnClickListener { pedirRol() }
        }

        layout.addView(texto)
        layout.addView(boton)
        setContentView(layout)
    }

    private fun pedirRol() {
        val roleManager = getSystemService(RoleManager::class.java)

        if (roleManager.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING) &&
            !roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING)
        ) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            startActivityForResult(intent, REQ_ROLE)
        }
    }
}
