package ru.javarush.internship.parts.list.dao;

import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

public interface PartDao {
    Part getPartById(int id);

    Part addPart(Part part);

    Part updatePartById(int id, Part part);

    void deletePartById(int id);

    PartList getPartList(Integer page, Integer size, String search, String required);
}
