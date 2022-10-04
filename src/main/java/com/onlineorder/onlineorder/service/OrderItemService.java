package com.onlineorder.onlineorder.service;

import com.onlineorder.onlineorder.dao.OrderItemDao;
import com.onlineorder.onlineorder.entity.Customer;
import com.onlineorder.onlineorder.entity.MenuItem;
import com.onlineorder.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemDao orderItemDao;

    public void saveOrderItem(int menuId) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);//通过menuId找到menuItem

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());//此处可以用来打折优惠，并不冗余
        orderItemDao.save(orderItem);//此处的Transaction已经被放进save中了
    }

}
