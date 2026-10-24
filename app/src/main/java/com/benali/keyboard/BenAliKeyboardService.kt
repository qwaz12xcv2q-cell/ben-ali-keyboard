
package com.benali.keyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class BenAliKeyboardService : InputMethodService() {

    private var english = false
    private var gold = true

    override fun onCreateInputView(): View {
        return buildKeyboard()
    }

    private fun buildKeyboard(): View {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(4, 8, 4, 8)
        root.setBackgroundColor(Color.rgb(18, 18, 18))

        val title = TextView(this)
        title.text = "بن علي • BEN ALI"
        title.textSize = 18f
        title.setTypeface(null, Typeface.BOLD)
        title.setTextColor(Color.rgb(255, 215, 0))
        root.addView(title)

        val rows = if (english) {
            listOf("QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM")
        } else {
            listOf("ضصثقفغعهخح", "شسيبلاتنم", "ئءؤرلاىةوز")
        }

        for (row in rows) {
            val line = LinearLayout(this)
            line.orientation = LinearLayout.HORIZONTAL

            for (letter in row) {
                val key = Button(this)
                key.text = letter.toString()
                key.textSize = 17f
                key.setTextColor(
                    if (gold) Color.rgb(255, 215, 0)
                    else Color.WHITE
                )
                key.setBackgroundColor(Color.rgb(45, 45, 45))

                val params = LinearLayout.LayoutParams(0, 52, 1f)
                params.setMargins(2, 3, 2, 3)
                line.addView(key, params)

                key.setOnClickListener {
                    currentInputConnection?.commitText(
                        letter.toString(), 1
                    )
                }
            }
            root.addView(line)
        }

        val controls = LinearLayout(this)
        controls.orientation = LinearLayout.HORIZONTAL

        controls.addView(makeButton("🌐") {
            english = !english
            setInputView(buildKeyboard())
        })

        controls.addView(makeButton("😊") {
            currentInputConnection?.commitText("😊", 1)
        })

        controls.addView(makeButton("مسافة") {
            currentInputConnection?.commitText(" ", 1)
        })

        controls.addView(makeButton("⌫") {
            currentInputConnection?.deleteSurroundingText(1, 0)
        })

        controls.addView(makeButton("لون") {
            gold = !gold
            setInputView(buildKeyboard())
        })

        root.addView(controls)
        return root
    }

    private fun makeButton(
        label: String,
        action: () -> Unit
    ): Button {
        val button = Button(this)
        button.text = label
        button.setOnClickListener { action() }
        return button
    }
}

