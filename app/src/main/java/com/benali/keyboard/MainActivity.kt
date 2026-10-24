
package com.benali.keyboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 48, 32, 32)
        layout.setBackgroundColor(0xFF111111.toInt())

        val title = TextView(this)
        title.text = "بن علي | BEN ALI"
        title.textSize = 28f
        title.setTextColor(0xFFFFD700.toInt())
        layout.addView(title)

        val info = TextView(this)
        info.text = "\nلوحة مفاتيحك العربية والإنجليزية"
        info.textSize = 18f
        info.setTextColor(-1)
        layout.addView(info)

        val settingsButton = Button(this)
        settingsButton.text = "تفعيل لوحة مفاتيح بن علي"
        settingsButton.setOnClickListener {
            startActivity(
                Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            )
        }
        layout.addView(settingsButton)

        val chooseButton = Button(this)
        chooseButton.text = "اختيار لوحة المفاتيح"
        chooseButton.setOnClickListener {
            val imm = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            imm.showInputMethodPicker()
        }
        layout.addView(chooseButton)

        setContentView(layout)
    }
}

