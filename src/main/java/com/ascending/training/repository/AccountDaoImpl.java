package com.ascending.training.repository;

import com.ascending.training.dao.AccountDao;
import com.ascending.training.model.Account;
import com.ascending.training.model.School;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    private Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);



    @Override
    public Account save(Account account) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(account);
            transaction.commit();
            session.close();

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }
        return account;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public boolean deleteByName(String accountName) {
        return false;
    }

    @Override
    public boolean delete(Account account) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(account);
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
    public List<Account> getAccounts() {
        String hql = "select distinct account From Account as account left join fetch account.orders";

        try (Session session = HibernateUtil.getSession()){
            Query<Account> query = session.createQuery(hql);

            return query.list();
        }
    }

    @Override
    public Account getAccountById(Long id) {
        if (id == null) return null;
        String hql = "FROM Account as a where a.id =:id";

        try (Session session = HibernateUtil.getSession()){
            Query<Account> query = session.createQuery(hql);
            query.setParameter("id", id);

            return query.uniqueResult();
        }
    }

    @Override
    public Account getAccountByName(String accountName) {
        if (accountName == null) return null;
        String hql = "FROM Account as a where a.accountName =:accountName";

        try (Session session = HibernateUtil.getSession()){
            Query<Account> query = session.createQuery(hql);
            query.setParameter("accountName", accountName);

            return query.uniqueResult();
        }
    }
}
