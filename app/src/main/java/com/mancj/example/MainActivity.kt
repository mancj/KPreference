package com.mancj.example

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.mancj.kpreference.BooleanPreference
import com.mancj.kpreference.IntPreference
import com.mancj.kpreference.PreferenceHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PreferenceHolder {
    override val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

    val isFirstLaunch by BooleanPreference(false)
    val customNamePreference by IntPreference(2, PREFERENCE_USER_AGE)

    companion object {
        private const val PREFERENCE_USER_AGE = "PREFERENCE_USER_AGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
