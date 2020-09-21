package com.ascending.training.repository;

import com.ascending.training.dao.ItemDao;
import com.ascending.training.model.Account;
import com.ascending.training.model.Item;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Override
    public Item save(Item item) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
            session.close();

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public boolean deleteByName(String itemName) {
        return false;
    }

    @Override
    public boolean delete(Item item) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
            session.close();
            deleteCount = 1;
            //        return school;
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }

        return deleteCount == 1;
    }

    @Override
    public List<Item> getItems() {
        String hql = "select distinct item From Item as item";

        try (Session session = HibernateUtil.getSession()){
            Query<Item> query = session.createQuery(hql);

            return query.list();
        }
    }

    @Override
    public Item getItemById(Long id) {
        if (id == null) return null;
        String hql = "FROM Item as i where i.id =:id";

        try (Session session = HibernateUtil.getSession()){
            Query<Item> query = session.createQuery(hql);
            query.setParameter("id", id);

            return query.uniqueResult();
        }
    }

    @Override
    public Item getItemByName(String itemName) {
        if (itemName == null) return null;
        String hql = "FROM Item as i where i.name =:itemName";

        try (Session session = HibernateUtil.getSession()){
            Query<Item> query = session.createQuery(hql);
            query.setParameter("itemName", itemName);

            return query.uniqueResult();
        }
    }
}
