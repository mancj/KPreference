package com.mancj.kpreference

import android.content.SharedPreferences
import kotlin.reflect.KProperty

open class KPreference<T>(
    private val defVal: T?,
    private val preferenceName: String? = null,
    private val clazz: Class<T>
) {

    @Suppress("UNCHECKED_CAST")
    open operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T = with(thisRef.preferences) {
        val key = preferenceName ?: property.name
        when (clazz) {
            Int::class.java -> getInt(key, defVal as Int) as T
            Long::class.java -> getLong(key, defVal as Long) as T
            Float::class.java -> getFloat(key, defVal as Float) as T
            String::class.java -> getString(key, (defVal as? String)) as T
            Boolean::class.java -> getBoolean(key, defVal as Boolean) as T
            else -> null!!
        }
    }

    open operator fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        val key = preferenceName ?: property.name
        thisRef.preferences.edit().run {
            when (clazz) {
                Int::class.java -> putInt(key, value as Int)
                Long::class.java -> putLong(key, value as Long)
                Float::class.java -> putFloat(key, value as Float)
                String::class.java -> putString(key, value as String)
                Boolean::class.java -> putBoolean(key, value as Boolean)
            }
            this
        }.apply()
    }
}

interface PreferenceHolder {
    val preferences: SharedPreferences
}

class IntPreference(defVal: Int, preferenceName: String? = null) :
    KPreference<Int>(defVal, preferenceName, Int::class.java)

class LongPreference(defVal: Long, preferenceName: String? = null) :
    KPreference<Long>(defVal, preferenceName, Long::class.java)

class FloatPreference(defVal: Float, preferenceName: String? = null) :
    KPreference<Float>(defVal, preferenceName, Float::class.java)

class BooleanPreference(defVal: Boolean, preferenceName: String? = null) :
    KPreference<Boolean>(defVal, preferenceName, Boolean::class.java)

class StringPreference(defVal: String? = null, preferenceName: String? = null) :
    KPreference<String>(defVal, preferenceName, String::class.java)
