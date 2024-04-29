package ru.mtsbank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.service.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreatureDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Creature creature){
        try (Session session = sessionFactory.getCurrentSession()){
            session.merge(creature);
        }
    }

    public List<Creature> findAll(){

        List<Creature> result;
        Session currentSession =   sessionFactory.getCurrentSession();
        Transaction tr = currentSession.beginTransaction();
        result = currentSession.createQuery("from " + Creature.class.getName(),Creature.class).list();
        tr.commit();
        return result;
    }
}
