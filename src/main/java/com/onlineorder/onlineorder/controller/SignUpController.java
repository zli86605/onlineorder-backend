package com.onlineorder.onlineorder.controller;

import com.onlineorder.onlineorder.entity.Customer;
import com.onlineorder.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {
    private CustomerService customerService;

    @Autowired
    public SignUpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)//定义 REST API
    @ResponseStatus(value = HttpStatus.CREATED)//处理完之后返回response
    public void signUp(@RequestBody Customer customer) {
        customerService.signUp(customer);
    }

}
