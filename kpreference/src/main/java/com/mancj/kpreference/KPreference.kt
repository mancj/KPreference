package com.mancj.kpreference

import android.content.SharedPreferences
import kotlin.reflect.KProperty

open class KPreference<T>(private val defVal: T?, private val clazz: Class<T>) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: PreferenceHolder, property: KProperty<*>): T = with(thisRef.preferences) {
        val key = property.name
        when (clazz) {
            Int::class.java -> getInt(key, defVal as Int) as T
            Long::class.java -> getLong(key, defVal as Long) as T
            Float::class.java -> getFloat(key, defVal as Float) as T
            String::class.java -> getString(key, defVal as String) as T
            Boolean::class.java -> getBoolean(key, defVal as Boolean) as T
            else -> null!!
        }
    }

    operator fun setValue(thisRef: PreferenceHolder, property: KProperty<*>, value: T) {
        val key = property.name
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

class IntPreference(defVal: Int) : KPreference<Int>(defVal, Int::class.java)
class LongPreference(defVal: Long) : KPreference<Long>(defVal, Long::class.java)
class FloatPreference(defVal: Float) : KPreference<Float>(defVal, Float::class.java)
class BooleanPreference(defVal: Boolean) : KPreference<Boolean>(defVal, Boolean::class.java)
class StringPreference(defVal: String? = null) : KPreference<String>(defVal, String::class.java)
