package ru.javarush.internship.parts.list.service;

import ru.javarush.internship.parts.list.model.Part;

import java.util.List;

public interface PartService {
    Part getPartById(int id);

    void addPart(Part part);

    void updatePartById(int id, Part part);

    void deletePartById(int id);

    List<Part> getPartList();
}
