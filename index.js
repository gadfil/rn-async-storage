import {NativeModules, Platform, AsyncStorage} from 'react-native';

if(Platform.OS === 'ios'){
    module.exports =AsyncStorage;
}else {
    module.exports = NativeModules.RNAsyncStorage;
}

