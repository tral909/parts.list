package ru.javarush.internship.parts.list.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;

import java.util.List;

@Repository("databaseDao")
public class DataBasePartDao implements PartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Part getPartById(Long id) {
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
    public Part updatePartById(Long id, Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        return part;
    }

    @Override
    public void deletePartById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = session.load(Part.class, id);
        if (part != null) {
            session.delete(part);
        }
    }

    @Override
    public List<Part> filterParts(Integer page, Integer size, String search, Boolean required) {
        Session session = sessionFactory.getCurrentSession();

        //search and filter
        //todo выбираем все запчасти, неправильно, надо переделать пагинацию
        Query query = buildQuery(session, search, required);
        List<Part> parts = query.list();

        //paginate
        Query paginatedQuery = addPaginationToQuery(query, page, size, parts);

        return paginatedQuery.list();
    }

    @Override
    public int canAssemblyComps() {
        //how much comps can assembly
        Session session = sessionFactory.getCurrentSession();
        Query queryForAssemblyComps = session
                .createQuery("SELECT min(amount) FROM Part WHERE required = true");
        return (int) queryForAssemblyComps.getSingleResult();
    }

    // todo refactor using stringbuilder
    private Query buildQuery(Session session, String search, Boolean required) {
        Query query;
        if ((search == null || search.isEmpty()) && required == null) {
            query = session.createQuery("FROM Part");
        } else if (search == null || search.isEmpty()) {
            query = session.createQuery("FROM Part WHERE required = :req");
            query.setParameter("req", required);
        } else if (required == null) {
            query = session.createQuery("FROM Part WHERE name LIKE :sbstring");
            query.setParameter("sbstring", "%" + search + "%");
        } else {
            query = session.createQuery("FROM Part WHERE required = :req AND name LIKE :sbstring");
            query.setParameter("req", required);
            query.setParameter("sbstring", "%" + search + "%");
        }
        return query;
    }

    private Query addPaginationToQuery(Query query, Integer page, Integer size, List<Part> parts) {
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
