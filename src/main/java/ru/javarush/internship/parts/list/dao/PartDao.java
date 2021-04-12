package ru.javarush.internship.parts.list.dao;

import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

public interface PartDao {
    Part getPartById(Long id);

    Part addPart(Part part);

    Part updatePartById(Long id, Part part);

    void deletePartById(Long id);

    PartList getPartsList(Integer page, Integer size, String search, Boolean required);
}
