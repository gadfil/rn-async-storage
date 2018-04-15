
# rn-async-storage
## Why?
AsyncStorage not fulfilling promise on Android 7+<br>
I hope this problem will be solved, but issue https://github.com/facebook/react-native/issues/14101 was created  at 22 May 2017. <br>
Today 15 Apr 2018<br>
This is very quickly solution for android

Just replace
```javascript
import {AsyncStorage} from 'react-native'
```
 to
 ```javascript
 import {AsyncStorage} from 'rn-async-storage'
```
 .. thats all! :)

For ios you can use all function of AsyncStorage

### sorry but only works
 - setItem(key, value)
 - getItem(key)
 - getAllKeys()
 - clear()

If you need other methods from AsynStorage let me know

For ios you can use all function from AsynStorage
## Getting started
`$ npm install rn-async-storage --save`

or

`$ yarn add rn-async-storage`

### Mostly automatic installation

`$ react-native link react-native-rn-async-storage`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAsyncStoragePackage;` to the imports at the top of the file
  - Add `new RNAsyncStoragePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':rn-async-storage'
  	project(':rn-async-storage').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-rn-async-storage/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':rn-async-storage')
  	```

