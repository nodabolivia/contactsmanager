package com.cursoandroid.practico2v2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)
        setupActionBarWithNavController(
            findNavController(R.id.fragment)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

}