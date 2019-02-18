import {NativeModules} from 'react-native';

const RFile = NativeModules.RFile;

export default class File {

    constructor(filePath) {
        this.filePath = filePath;
    }

    exists() {
        return RFile.exists(this.filePath);
    }

    name() {
        return RFile.getFileName(this.filePath);
    }

    delete() {
        return RFile.delete(this.filePath);
    }

    canRead() {
        return RFile.canRead(this.filePath);
    }

    canWrite() {
        return RFile.canWrite(this.filePath);
    }

    length() {
        return RFile.length(this.filePath);
    }

    lastModified() {
        return RFile.lastModified(this.filePath);
    }

    setLastModified(time) {
        return RFile.setLastModified(this.filePath, time);
    }

}
