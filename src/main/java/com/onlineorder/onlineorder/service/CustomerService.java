package com.onlineorder.onlineorder.service;

import com.onlineorder.onlineorder.dao.CustomerDao;
import com.onlineorder.onlineorder.entity.Cart;
import com.onlineorder.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerDao customerDao;//CustomerService 现在可以调用customerDao的方法了
                                    //此处是将逻辑层和data层关联
    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);

        customer.setEnabled(true);

        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }


}
