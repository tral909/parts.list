package ru.javarush.internship.parts.list.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.*;

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

    public PartList getPartList(Integer page, Integer size) {
        PartList partList = new PartList();

        //create num of canAssemblyComps
        partList.setList(mockPartList);
        int maxCanAssemblyComps = Integer.MAX_VALUE;
        for (Part part : mockPartList) {
            if (part.isRequired() && part.getAmount() < maxCanAssemblyComps) {
                maxCanAssemblyComps = part.getAmount();
            }
        }
        partList.setCanAssemblyComps(maxCanAssemblyComps);

        //pagination implementation
        if (size == null || size < 1 || mockPartList.size() > 10) {
            size = 10;
        } else {
            size = mockPartList.size();
        }
        List<List<Part>> paginatedData = getPages(mockPartList, size);
        if (page == null || page < 1 || page > paginatedData.size()) {
            page = 1;
        }
        partList.setList(paginatedData.get(--page));
        return partList;
    }

    private static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        List<List<T>> pages = new ArrayList<List<T>>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }

/*    private void checkId(int id) throws Exception {
        if (id < 0 || id >= mockPartList.size()) {
            throw new NoSuchElementException("No part with id = " + id);
        }
    }*/
}
