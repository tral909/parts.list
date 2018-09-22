package ru.javarush.internship.parts.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.internship.parts.list.dao.PartDao;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    @Qualifier("databaseDao")
    private PartDao partDao;

    @Transactional
    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }

    @Transactional
    public Part addPart(Part part) {
        return partDao.addPart(part);
    }

    @Transactional
    public Part updatePartById(int id, Part part) {
        return partDao.updatePartById(id, part);
    }

    @Transactional
    public void deletePartById(int id) {
        partDao.deletePartById(id);
    }

    @Transactional
    public PartList getPartList(Integer page, Integer size, String search, String required) {
        return partDao.getPartList(page, size, search, required);
    }
}
