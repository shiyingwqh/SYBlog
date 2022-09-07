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
public class Blog {
    String id;
    String text;
    int like;
    int dislike;
    long upTime;
    long modifyTime;
}
