package com.wuqihang.syblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wuqihang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private User user;
    private String name;
    private String tel;
    private String address;
    private String remarks;
    private String email;
}
