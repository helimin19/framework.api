package com.tongshi.framework.api.vo;

import com.tongshi.framework.api.constant.HttpStatusConstants;

import lombok.Data;

@Data
public class ResponseVo {

	private int status = 1;
	
	private String msg = "";
	
	private Object data;
	
	public ResponseVo(Object data) {
		this.data = data;
	}
	
	public ResponseVo(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public ResponseVo(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	/**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseVo success() {
        return ResponseVo.success(true);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseVo success(Object data) {
        return new ResponseVo(data);
    }

    /**
     * 返回错误消息
     *
     *@param code 状态码
     * @return ResposeVo
     */
    public static ResponseVo error(int code) {
        return ResponseVo.error(code, "");
    }

    /**
     * 返回错误消息
     *
     * @param msg  错误内容
     * @return ResposeVo
     */
    public static ResponseVo error(String msg) {
        return new ResponseVo(HttpStatusConstants.ERROR, msg);
    }
    
    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  错误内容
     * @return ResposeVo
     */
    public static ResponseVo error(int code, String msg) {
        return new ResponseVo(code, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  错误内容
     * @param data 错误数据
     * @return ResposeVo
     */
    public static ResponseVo error(int code, String msg, Object data) {
        return new ResponseVo(code, msg, data);
    }
    
}
