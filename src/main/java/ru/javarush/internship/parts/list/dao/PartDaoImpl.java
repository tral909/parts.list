package ru.javarush.internship.parts.list.dao;

import ru.javarush.internship.parts.list.model.Part;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class PartDaoImpl implements PartDao {
    private List<Part> mockPartList = new ArrayList<Part>(Arrays.asList(new Part(1, "Motherboard", true, 3),
            new Part(2, "Soundboard", false, 2),
            new Part(3, "Network adapter", false, 5),
            new Part(4, "CPU", true, 7),
            new Part(5, "RAM", true, 4),
            new Part(6, "Videocard", true, 2),
            new Part(7, "HDD", true, 8)));

    public Part getPartById(int id) throws Exception {
        checkId(id);
        return mockPartList.get(id);
    }

    public void addPart(Part part) throws Exception {
        mockPartList.add(part);
    }

    public void updatePartById(int id, Part part) throws Exception {
        checkId(id);
        mockPartList.set(id, part);
    }

    public void deletePartById(int id) throws Exception {
        checkId(id);
        mockPartList.remove(id);
    }

    public List<Part> getPartList() throws Exception {
        return mockPartList;
    }

    private void checkId(int id) throws NoSuchElementException {
        if (id < 0 || id >= mockPartList.size()) {
            throw new NoSuchElementException("No part with id = " + id);
        }
    }
}
