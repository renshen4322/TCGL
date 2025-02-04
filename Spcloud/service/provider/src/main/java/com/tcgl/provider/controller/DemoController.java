package com.tcgl.provider.controller;

import com.tcgl.common.util.QRCodeUtil;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;

/**
 * 11'
 *
 * @author Shuguang_Liux
 * @date 2021/10/29 18:25
 */
@RestController
//@RequestMapping("/provider")
@RefreshScope
public class DemoController {
    @GetMapping(value = "/demo/{string}")
    public String demoTesting(@PathVariable("string") String string) throws Exception {
        QRCodeUtil.createImage("",null,true);
        return "hello" + string;
    }


}
