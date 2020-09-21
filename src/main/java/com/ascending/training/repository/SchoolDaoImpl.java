package com.ascending.training.repository;

import com.ascending.training.dao.SchoolDao;
import com.ascending.training.model.School;
import com.ascending.training.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class SchoolDaoImpl implements SchoolDao {
    private Logger logger = LoggerFactory.getLogger(SchoolDaoImpl.class);

    @Override
    public School save(School school) {
       Transaction transaction = null;
//       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//       Session session = sessionFactory.openSession();
        Session session = HibernateUtil.getSession();

       try{
           transaction = session.beginTransaction();
           session.saveOrUpdate(school);
           transaction.commit();
           session.close();
   //        return school;
       }catch (Exception e){
           if(transaction != null)
               transaction.rollback();
           logger.error("fail to insert record, error={}", e.getMessage());
           session.close();
       }
        return school;
    }

    @Override
    public School update(School school) {
        return null;
    }

    @Override
    public boolean deleteByName(String schoolname) {

        if (schoolname == null) return false;
        String hql = "DELETE School as s where s.name =: schoolname";

        try (Session session = HibernateUtil.getSession()){
            Query<School> query = session.createQuery(hql);
            query.setParameter("schoolname", schoolname);

           return true;
        }

    }

    @Override
    public boolean delete(School school) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        int deleteCount = 0;

        try{
            transaction = session.beginTransaction();
            session.delete(school);
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
    public List<School> getschools() {
        //String hql = "FROM School";
        String hql = "select distinct school From School as school left join fetch school.accounts";

        try (Session session = HibernateUtil.getSession()){
            Query<School> query = session.createQuery(hql);

            return query.list();
        }
    }

    @Override
    public School getSchoolById(Long id) {
        if (id == null) return null;
        String hql = "FROM School as s where s.id =:id";

        try (Session session = HibernateUtil.getSession()){
            Query<School> query = session.createQuery(hql);
            query.setParameter("id", id);

            return query.uniqueResult();
        }
    }

    @Override
    public List<School> getDepartmentsWithChildren() {
       return null;
    }

    @Override
    public School getSchoolByName(String schoolName) {
        if (schoolName == null) return null;
        String hql = "FROM School as s where s.name =:schoolName";

        try (Session session = HibernateUtil.getSession()){
            Query<School> query = session.createQuery(hql);
            query.setParameter("schoolName", schoolName);

            return query.uniqueResult();
        }
    }
}
