import {NativeModules, Platform, AsyncStorage} from 'react-native';

if(Platform.OS === 'iod'){
    module.exports =AsyncStorage;
}else {
    module.exports = NativeModules.RNAsyncStorage;
}

