package com.marshall.sky.user.exception;

import com.marshall.sky.core.exception.ExFactor;
import com.marshall.sky.core.exception.SkyExceptionMsgModel;

public enum SkyUserExceptionEnum implements ExFactor {
  DEFAULT(1, "default error", "默认异常"),
  USER_IS_EXIST(2, "user is exist", "用户不存在鸭。"),
  ;

  int errorIndex;
  String errorCode;
  String errorMsg;

  SkyUserExceptionEnum(int errorIndex, String errorCode, String errorMsg) {
    this.errorIndex = errorIndex;
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public int getErrorIndex() {
    return errorIndex;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }


  @Override
  public SkyExceptionMsgModel getModel() {
    return new SkyExceptionMsgModel(errorIndex, errorCode, errorMsg, "", "");
  }


}
