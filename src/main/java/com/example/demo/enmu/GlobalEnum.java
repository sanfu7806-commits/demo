package com.example.demo.enmu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 全局异常响应
 * @Author: 胡启力
 * @Date: 2021/5/17 16:50
 */
@Getter
@AllArgsConstructor
public enum GlobalEnum {

    // 未知错误（自定义）
    UNKNOWN(-1, "未知错误！"),
    SUCCESS(200, "请求成功！"),
    ERROR400(400, "错误请求！"),
    ERROR401(401, "没有授权！"),
    ERROR404(404, "服务不存在！"),
    ERROR403(403, "拒绝访问！"),
    ERROR405(405, "方法不被允许！"),
    ERROR406(406, "不接受的请求！"),
    ERROR500(500, "服务器内部错误！"),
    NOTLOGIN(0, "用户未登录或登录失效！"),
    // sentinel枚举
    FLOW(100, "接口限流了！"),
    DEGRADE(101, "服务降级了！"),
    PARAM_FLOW(102, "热点参数限流了！"),
    SYSTEM_BLOCK(103, "触发系统保护规则了！"),
    AUTHORITY(104, "授权规则不通过！");

    private final Integer code;
    private final String message;

}
