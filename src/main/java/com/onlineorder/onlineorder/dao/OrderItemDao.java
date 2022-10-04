package com.onlineorder.onlineorder.dao;

import com.onlineorder.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(OrderItem orderItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orderItem);//写在表里
            session.getTransaction().commit();//将上面save的内容都真正存入表中

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();//如果try中的内容出错，在此处rollback回滚
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
