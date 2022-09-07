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
    int code;
    String massage;
    Object data;
    public ResponsePKG(Object data) {
        this.data = data;
    }
}
