package com.wooden.keyboard

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 101)
        }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.parseColor("#140b05"))
            setPadding(48, 48, 48, 48)
        }

        val title = TextView(this).apply {
            text = "Pure Wooden Keyboard"
            textSize = 26f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.parseColor("#c89668"))
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Step 1: Enable in Settings\nStep 2: Select Wooden Keyboard"
            textSize = 15f
            setTextColor(Color.parseColor("#9e6b41"))
            gravity = Gravity.CENTER
            setPadding(0, 18, 0, 48)
        }

        val btnEnable = createWoodButton("1. Enable Wooden Keyboard") {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        val btnSelect = createWoodButton("2. Select Wooden Keyboard") {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }

        val testBox = EditText(this).apply {
            hint = "Tap here to test typing..."
            setHintTextColor(Color.parseColor("#7a4a29"))
            setTextColor(Color.parseColor("#e3be91"))
            setPadding(32, 28, 32, 28)
            background = GradientDrawable().apply {
                setColor(Color.parseColor("#231106"))
                cornerRadius = 24f
                setStroke(3, Color.parseColor("#86542f"))
            }
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.topMargin = 48
            layoutParams = lp
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(btnEnable)
        root.addView(btnSelect)
        root.addView(testBox)
        setContentView(root)
    }

    private fun createWoodButton(label: String, onClick: () -> Unit): Button {
        return Button(this).apply {
            text = label
            textSize = 16f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.parseColor("#1f0c02"))
            isAllCaps = false
            background = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(Color.parseColor("#a36d43"), Color.parseColor("#734524"))
            ).apply {
                cornerRadius = 28f
                setStroke(3, Color.parseColor("#2b1407"))
            }
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.bottomMargin = 28
            layoutParams = lp
            setOnClickListener { onClick() }
        }
    }
}
