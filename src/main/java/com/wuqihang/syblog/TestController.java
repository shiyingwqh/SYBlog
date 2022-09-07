package com.wuqihang.syblog;

import com.wuqihang.syblog.mapper.BlogMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wuqihang
 */
@Controller
@RequestMapping("api")
public class TestController {

    @PostMapping("/aaa")
    @ResponseBody
    public String a(@RequestBody String a){
        return a + "OK";
    }
}
