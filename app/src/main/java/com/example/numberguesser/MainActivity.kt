package com.example.numberguesser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Number)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Generate a random number between 1 and 100
        randomNumber = (1..100).random()

        val editText = findViewById<EditText>(R.id.editTextNumber)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textview)

        button.setOnClickListener {
            val guessText = editText.text.toString()

            if (guessText.isNotEmpty()) {
                val userGuess = guessText.toInt()

                when {
                    userGuess == randomNumber -> {
                        textView.text = getString(R.string.msg_correct)
                    }
                    userGuess < randomNumber -> {
                        textView.text = getString(R.string.msg_too_low)
                    }
                    else -> {
                        textView.text = getString(R.string.msg_too_high)
                    }
                }
                // Clear the input after a guess
                editText.text.clear()
            } else {
                textView.text = getString(R.string.msg_enter_number)
            }
        }
    }
}
