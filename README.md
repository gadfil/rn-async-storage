
# react-native-async-storage
## Why?
AsyncStorage not fulfilling promise on Android 7+<br>
I hope this problem will be solved, but issue https://github.com/facebook/react-native/issues/14101 was created  at 22 May 2017. <br>
Today 15 Apr 2018<br>
This is very quickly solution for android <br>
Just replace
```javascript
import {AsyncStorage} from 'react-native'
```
 to
 ```javascript
 import {AsyncStorage} from 'react-native-async-storage'
```
 .. thats all! :)
### sorry but only works
 - setItem(key, value)
 - getItem(key)
 - getAllKeys()
 - clear()

If you need other methods from AsynStorage let me know
## Getting started
`$ npm install react-native-async-storage --save`
<br>or<br>
`$ yarn add react-native-async-storage`

### Mostly automatic installation

`$ react-native link react-native-react-native-async-storage`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAsyncStoragePackage;` to the imports at the top of the file
  - Add `new RNAsyncStoragePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-async-storage'
  	project(':react-native-async-storage').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-async-storage/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-async-storage')
  	```

