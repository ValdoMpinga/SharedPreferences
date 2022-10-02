package com.example.sharedpreferencesdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity()
{
    private lateinit var nameView: EditText
    private lateinit var ageView: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameView = findViewById(R.id.etName)
        ageView = findViewById(R.id.etAge)
        sharedPreferences = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun onPause()
    {
        super.onPause()
        val name = nameView.text.toString()
        val age = ageView.text.toString().toInt()

        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit()
        }
    }

    override fun onResume()
    {
        super.onResume()
        val name = sharedPreferences.getString("sf_name",null)
        val age = sharedPreferences.getInt("sf_age",0)

        nameView.setText(name)

        if(age != 0)
        ageView.setText(age.toString())
    }
}