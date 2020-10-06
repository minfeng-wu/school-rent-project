package com.ascending.training.repository;

import com.ascending.training.dao.UserDao;
import com.ascending.training.model.Role;
import com.ascending.training.model.User;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User save(User user) {
        Transaction transaction = null;
//       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//       Session session = sessionFactory.openSession();
        Session session = HibernateUtil.getSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();

        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
            logger.error("fail to insert record, error={}", e.getMessage());
            session.close();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(Long Id) {
        return null;
    }

    @Override
    public User getUserByCredentials(String email, String password) throws Exception {
        String hql = "FROM User as u join fetch u.roles where lower(u.email) =:email and u.password =:password";
        logger.debug(String.format("User email: %s, password: %s", email, password));

        //finally generate to close
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password",password);

            return query.uniqueResult();
        }
        catch(Exception e){
            logger.debug("can't find user record or session in getUserByCredentials");
            throw new Exception("can't find user record or session");
        }
    }

    @Override
    public User findUserBynameAndEmail(String name, String email) throws Exception {
        String hql = "FROM User as u join fetch u.roles where lower(u.email) =:email and u.name =:name";
        logger.debug(String.format("User name: %s, email: %s", name, email));

        //finally generate to close
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("name",name);

            return query.uniqueResult();
        }
        catch(Exception e){
            logger.debug("can't find user record or session in getUserByCredentials");
            throw new Exception("can't find user record or session");
        }
    }

    @Override
    public User addRole(User user, Role role) {
        return null;
    }

    @Override
    public int delete(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(user);
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
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }
}
