package com.ascending.training.repository;

import com.ascending.training.dao.ItemDao;
import com.ascending.training.dao.OrderDao;
import com.ascending.training.model.Order;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Override
    public Order save(Order order) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();
            session.close();

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(Order order) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            session.close();
            deleteCount = 1;

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }

        return deleteCount == 1;
    }

    @Override
    public List<ItemDao> getOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }
}
