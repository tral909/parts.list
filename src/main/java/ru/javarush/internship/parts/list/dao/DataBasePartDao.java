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
    private static final String REQUIRED_TRUE = "true";
    private static final String REQUIRED_FALSE = "false";

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

        //search and filter
        Query filAndSearQuery = getFilterAndSearchQuery(session, search, required);
        List<Part> parts = filAndSearQuery.list();

        //paginate
        Query pagQuery = getPaginatedQuery(filAndSearQuery, page, size, parts);
        partList.setList(pagQuery.list());

        //how much comps can assembly
        Query queryForAssemblyComps = session.createQuery("SELECT min(amount) FROM Part WHERE required = true");
        int min = (int) queryForAssemblyComps.getSingleResult();
        partList.setCanAssemblyComps(min);

        return partList;
    }

    private Query getFilterAndSearchQuery(Session session, String search, String required) {
        Query query = null;
        if ((search == null || search.isEmpty()) && (required == null || required.isEmpty())) {
            query = session.createQuery("FROM Part");
        } else if ((search == null || search.isEmpty()) && (required.equals(REQUIRED_TRUE) || required.equals(REQUIRED_FALSE))) {
            query = session.createQuery("FROM Part WHERE required = :req");
            query.setParameter("req", Boolean.valueOf(required));
        } else if (required == null || required.isEmpty()) {
            query = session.createQuery("FROM Part WHERE name LIKE :sbstring");
            query.setParameter("sbstring", "%" + search + "%");
        } else if ((search != null || !search.isEmpty()) && (required.equals(REQUIRED_TRUE) || required.equals(REQUIRED_FALSE))) {
            query = session.createQuery("FROM Part WHERE required = :req AND name LIKE :sbstring");
            query.setParameter("req", Boolean.valueOf(required));
            query.setParameter("sbstring", "%" + search + "%");
        }
        return query;
    }

    private Query getPaginatedQuery(Query query, Integer page, Integer size, List<Part> parts) {
        if (size == null || size < 1 || size > parts.size()) {
            size = parts.size();
        }
        if (page == null || page < 1 || page > parts.size()) {
            page = 1;
        }
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query;
    }
}
