package ru.javarush.internship.parts.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.internship.parts.list.dao.PartDao;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    @Autowired
    @Qualifier("databaseDao")
    private PartDao partDao;

    public Part getPartById(Long id) {
        return partDao.getPartById(id);
    }

    public Part addPart(Part part) {
        return partDao.addPart(part);
    }

    public Part updatePartById(Long id, Part part) {
        return partDao.updatePartById(id, part);
    }

    public void deletePartById(Long id) {
        partDao.deletePartById(id);
    }

    public PartList getPartList(Integer page, Integer size, String search, Boolean required) {
        if (search != null) {
            search = search.trim();
        }

        return partDao.getPartsList(page, size, search, required);
    }
}
