export default class LoganConfig {

    constructor() {
        this.configs = {};
    }

    /**
     * mmap缓存路径
     */
    setCachePath(cachePath) {
        this.configs['cachePath'] = cachePath;
        return this;
    }

    /**
     * file文件路径
     */
    setPath(path) {
        this.configs['path'] = path;
        return this;
    }

    /**
     * 删除文件最大值
     */
    setMaxFile(maxFile) {
        this.configs['maxFile'] = maxFile;
        return this;
    }

    /**
     * 删除天数
     * @param day
     * @returns {LoganConfig}
     */
    setDay(day) {
        this.configs['day'] = day;
        return this;
    }

    /**
     * 128位ase加密Key
     * @param encryptKey16
     * @returns {LoganConfig}
     */
    setEncryptKey16(encryptKey16) {
        this.configs['encryptKey16'] = encryptKey16;
        return this;
    }

    /**
     * 128位aes加密IV
     * @param encryptIv16
     * @returns {LoganConfig}
     */
    setEncryptIv16(encryptIv16) {
        this.configs['encryptIv16'] = encryptIv16;
        return this;
    }

    setMinSDCard(minSDCard) {
        this.configs['minSDCard'] = minSDCard;
        return this;
    }

    getConfigs() {
        return this.configs;
    }

}