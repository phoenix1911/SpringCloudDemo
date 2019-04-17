package com.imooc.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tjl on 2019/4/16 14:50.
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is 'product' msg";
    }
}
