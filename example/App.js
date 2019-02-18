import React, {Component} from 'react';
import {View, Button} from 'react-native';
import Logan, {LoganConfig} from '../lib/Logan';

Logan.init(new LoganConfig()
    .setCachePath('/data/user/0/com.example/files')
    .setPath('/storage/emulated/0/Android/data/com.example/files/rnlogan')
    .setEncryptKey16('0123456789')
    .setEncryptIv16('0123456789'));

Logan.debugEnable(true);

export default class App extends Component {

    onWrite = () => {
        Logan.write('Hello Logan', 1);
        Logan.flush();
    };

    onSend = () => {
        const now = new Date();
        const year = now.getFullYear();
        const month = format10(now.getMonth() + 1);
        const day = format10(now.getDate());
        Logan.send([year + '-' + month + '-' + day], async function(file) {
            console.log(await file.name());
            console.log(await file.exists());
        });
    };

    getAllFilesInfo = async () => {
        const filesInfo = await Logan.getAllFilesInfo();
        for(let key in filesInfo) {
            console.log(key + ':' + filesInfo[key]);
        }
    };

    render() {
        return (
            <View>
                <Button title="记录日志" onPress={this.onWrite}/>
                <Button title="发送日志" onPress={this.onSend}/>
                <Button title="获取文件信息" onPress={this.getAllFilesInfo}/>
            </View>
        );
    }
}

function format10(num) {
    if(num < 10) {
        return '0' + num;
    }
    return '' + num;
}
