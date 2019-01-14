# KPreference ⚙️
 <a href='https://bintray.com/mancj/maven/kpreference/_latestVersion'><img src='https://api.bintray.com/packages/mancj/maven/kpreference/images/download.svg'></a>


**Edit SharedPreferences as easy as possible!💥🔥**
---
No need to interact with some **preference wrappers** or override a **setters**, simply declare 
the **variables** right in your repository or other classes!!!

The only thing to do is to implement the `PreferenceHolder` 
interface and override the `SharedPreferences`

```kotlin
class ServiceRepository @Inject constructor(
    override val preferences: SharedPreferences /* override this */
) : PreferenceHolder { /* implement this interface */
   
   // all preferences will be saved to the `preferences` object 
   val isFirstLaunch: Boolean by BooleanPreference(false) // false - default value
  
   val userToken: String by StringPreference("default value")
   
   //or even easier, without specifying the type
    val someIntPreference by IntPreference(5)
    
}
```

Add library to your project. 
```gradle
dependencies {
    ...
    implementation "com.mancj:kpreference:$latestVersion"
}
```
<a href='https://bintray.com/mancj/maven/kpreference/_latestVersion'><img src='https://api.bintray.com/packages/mancj/maven/kpreference/images/download.svg'></a>

# 🤩🤩🤩
