package com.tongshi.framework.api.constant;

/**
 * 返回状态码
 */
public interface HttpStatusConstants {
	/**
     * 操作成功
     */
    final int SUCCESS = 200;

    /**
     * 未授权
     */
    final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    final int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    final int BAD_METHOD = 405;

    /**
     * 不支持的数据，媒体类型
     */
    final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    final int ERROR = 500;

    /**
     * 接口未实现
     */
    final int NOT_IMPLEMENTED = 501;
    
    /**
     * 参数错误
     */
    final int ILLEGAL_ARGUMENT  = 1001;
    
    /**
     * 验证失败
     */
    final int VALID_ERROR  = 2001;
    
    
}
