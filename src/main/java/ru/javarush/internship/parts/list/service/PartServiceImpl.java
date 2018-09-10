package ru.javarush.internship.parts.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.internship.parts.list.dao.PartDao;
import ru.javarush.internship.parts.list.model.Part;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    private PartDao partDao;

    public Part getPartById(int id) {
        Part part = null;
        try {
            part = partDao.getPartById(id);
        } catch (Exception e) {
            Logger.getLogger(PartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return part;
    }

    public void addPart(Part part) {
        try {
            partDao.addPart(part);
        } catch (Exception e) {
            Logger.getLogger(PartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updatePartById(int id, Part part) {
        try {
            partDao.updatePartById(id, part);
        } catch (Exception e) {
            Logger.getLogger(PartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deletePartById(int id) {
        try {
            partDao.deletePartById(id);
        } catch (Exception e) {
            Logger.getLogger(PartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Part> getPartList() {
        List<Part> partList = null;
        try {
            partList = partDao.getPartList();
        } catch (Exception e) {
            Logger.getLogger(PartServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return partList;
    }
}
