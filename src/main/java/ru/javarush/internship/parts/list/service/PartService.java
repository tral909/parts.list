package ru.javarush.internship.parts.list.service;

import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

public interface PartService {
    Part getPartById(int id);

    Part addPart(Part part);

    Part updatePartById(int id, Part part);

    int deletePartById(int id);

    PartList getPartList(Integer page, Integer size);
}
