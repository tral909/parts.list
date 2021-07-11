package ru.javarush.internship.parts.list.dao;

import ru.javarush.internship.parts.list.model.Part;

import java.util.List;

public interface PartDao {
    Part getPartById(Long id);

    Part addPart(Part part);

    Part updatePartById(Long id, Part part);

    void deletePartById(Long id);

    List<Part> filterParts(Integer page, Integer size, String search, Boolean required);

    int canAssemblyComps();
}
