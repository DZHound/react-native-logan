import {NativeModules} from 'react-native'
import File from './File';
import LoganConfig from './LoganConfig';

const RLogan = NativeModules.RLogan;
const {init, send, write, getAllFilesInfo, flush, debugEnable} = RLogan;

export { LoganConfig, File };

export default {
    init(config) {
        if(config) {
            init(config.getConfigs());
        }
    },

    send(dates, runnable) {
        send(dates, function(filePath) {
            runnable(new File(filePath));
        });
    },

    getAllFilesInfo,
    write,
    flush,
    debugEnable
}
