package ru.javarush.internship.parts.list.dao;

import ru.javarush.internship.parts.list.model.Part;

import java.util.List;

public interface PartDao {
    Part getPartById(int id) throws Exception;

    void addPart(Part part) throws Exception;

    void updatePartById(int id, Part part) throws Exception;

    void deletePartById(int id) throws Exception;

    List<Part> getPartList() throws Exception;
}
