package ru.javarush.internship.parts.list.service;

import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

public interface PartService {
    Part getPartById(Long id);

    Part addPart(Part part);

    Part updatePartById(Long id, Part part);

    void deletePartById(Long id);

    PartList getPartList(Integer page, Integer size, String search, Boolean required);
}
