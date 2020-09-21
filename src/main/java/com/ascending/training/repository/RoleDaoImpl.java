package com.ascending.training.repository;

import com.ascending.training.dao.RoleDao;
import com.ascending.training.model.Role;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public Role getRoleByName(String name) {
        return null;
    }

    @Override
    public Role save(Role role) {
        Transaction transaction = null;
//       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//       Session session = sessionFactory.openSession();
        Session session = HibernateUtil.getSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }
        return role;
    }

    @Override
    public int delete(Role role) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
            session.close();
            deleteCount = 1;

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }

        return deleteCount;
    }

    @Override
    public List<Role> findAllRoles() {
        return null;
    }
}
