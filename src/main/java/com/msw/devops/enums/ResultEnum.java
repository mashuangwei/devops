package com.msw.devops.enums;

/**
 * @author mashuangwei
 * @create 2017-12-27 17:39
 */
public enum ResultEnum {
    URL_NOT_FOUND(404, "Not Found"),
    PASSWORD_INCORRECT(200, "password is incorrect"),
    UNKNOW_ERROR(-1, "未知错误"),
    SERVER_ERROR(500, "服务器内部错误"),
    SUCCESS(0, "success"),
    SCHEDULER_ADD(1001,"添加定时任务失败"),
    SCHEDULER_RESUME(1002,"恢复定时任务失败"),
    SCHEDULER_DELETE(1003,"删除定时任务失败"),
    SCHEDULER_PAUSE(1004,"暂停定时任务失败"),
    SCHEDULER_UPDATE(1005,"更新定时任务失败"),
    GRPC_INIT_FAILED(1006,"grpc初始化连接失败"),
    GRPC_EXECUTE_FAILED(1006,"已有任务在执行中，请等待任务执行结束"),
    FILE_SAVE_FAILED(1007,"保存文件失败"),
    FOLDER_CREATE_FAILED(1008,"创建文件目录失败"),
    FILE_READ_ERROR(1009, "文件读取出错"),
    AUDIO_TEXT_DISMATCH(1010, "语音文件个数或者语音文本个数不匹配"),
    FILE_EXIST(1011, "文件已存在"),
    AUDIO_FILE_UP_ERROR(1011, "语音文件已存在，无法上传"),
    TEXT_FILE_UP_ERROR(1011, "请先上传语音文件"),
    ;

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
