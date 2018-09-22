package ru.javarush.internship.parts.list.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.List;

@Repository("databaseDao")
public class DataBasePartDao implements PartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Part getPartById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Part.class, id);
    }

    @Override
    public Part addPart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
        session.flush();
        return part;
    }

    @Override
    public Part updatePartById(int id, Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        return part;
    }

    @Override
    public void deletePartById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = session.load(Part.class, id);
        if (part != null) {
            session.delete(part);
        }
    }

    @Override
    public PartList getPartList(Integer page, Integer size, String search, String required) {
        Session session = sessionFactory.getCurrentSession();
        PartList partList = new PartList();
        List<Part> list = session.createQuery("FROM test.part").list();
        partList.setList(list);
        Query query = session.createQuery("SELECT min(test.part.amount) FROM test.part WHERE test.part.required = 1");
        int min = (int) query.getSingleResult();
        partList.setCanAssemblyComps(min);
        return partList;
    }
}
