package ru.javarush.internship.parts.list.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    private static List<Part> mockPartList = new ArrayList<Part>(Arrays.asList(
            new Part(1, "Motherboard", true, 3),
            new Part(2, "Soundboard", false, 2),
            new Part(3, "Network adapter", false, 5),
            new Part(4, "CPU", true, 7),
            new Part(5, "RAM", true, 4),
            new Part(6, "Videocard", true, 2),
            new Part(7, "HDD", true, 8)));

    public Part getPartById(int id) {
        //checkId(id);
        return mockPartList.get(--id);
    }

    public Part addPart(Part part) {
        int id = mockPartList.size();
        part.setId(++id);
        mockPartList.add(part);
        return mockPartList.get(--id);
    }

    public Part updatePartById(int id, Part part) {
        //checkId(id);
        mockPartList.set(--id, part);
        return mockPartList.get(id);
    }

    public int deletePartById(int id) {
        //checkId(id);
        mockPartList.remove(--id);
        return id;
    }

    public PartList getPartList() {
        PartList partList = new PartList();
        partList.setList(mockPartList);
        int maxCanAssemblyComps = Integer.MAX_VALUE;
        for (Part part : mockPartList) {
            if (part.isRequired() && part.getAmount() < maxCanAssemblyComps) {
                maxCanAssemblyComps = part.getAmount();
            }
        }
        partList.setCanAssemblyComps(maxCanAssemblyComps);
        return partList;
    }

/*    private void checkId(int id) throws Exception {
        if (id < 0 || id >= mockPartList.size()) {
            throw new NoSuchElementException("No part with id = " + id);
        }
    }*/
}
