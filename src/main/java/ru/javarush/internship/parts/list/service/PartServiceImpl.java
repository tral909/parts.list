package ru.javarush.internship.parts.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.internship.parts.list.dao.PartDao;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    private PartDao partDao;

    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }

    public Part addPart(Part part) {
        return partDao.addPart(part);
    }

    public Part updatePartById(int id, Part part) {
        return partDao.updatePartById(id, part);
    }

    public int deletePartById(int id) {
        return partDao.deletePartById(id);
    }

    public PartList getPartList(Integer page, Integer size, String search) {
        return partDao.getPartList(page, size, search);
    }
}
