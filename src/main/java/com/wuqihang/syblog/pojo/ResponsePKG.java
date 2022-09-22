package com.wuqihang.syblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wuqihang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePKG {
    public static ResponsePKG OK = new ResponsePKG(200, "OK", null);
    public static ResponsePKG ERROR_TOKEN_INVALID = new ResponsePKG(-1, "Token Invalid!", null);
    public static ResponsePKG ERROR_ILLEGAL_DATA = new ResponsePKG(-1, "Illegal Data!", null);


    int status;
    String msg;
    Object data;

    public ResponsePKG(Object data) {
        this.data = data;
    }

    public static ResponsePKG error(int status, String msg) {
        return new ResponsePKG(status, msg, null);
    }

    public static ResponsePKG ok() {
        return new ResponsePKG(200, "", null);
    }

    public static ResponsePKG ok(String msg) {
        return new ResponsePKG(200, msg, null);
    }

    public static ResponsePKG returnData(String msg, Object data) {
        return new ResponsePKG(200, msg, data);
    }

    public static ResponsePKG returnData(Object data) {
        return new ResponsePKG(200, "", data);
    }
}
