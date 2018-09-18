package ru.javarush.internship.parts.list.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.*;

@Repository
public class MockPartDaoImpl implements PartDao {
    private static final String REQUIRED_TRUE = "yes";
    private static final String REQUIRED_FALSE = "no";

    private static List<Part> mockPartList = new ArrayList<>(Arrays.asList(
            new Part(1, "Motherboard", true, 3),
            new Part(2, "Soundboard", false, 2),
            new Part(3, "Network adapter", false, 5),
            new Part(4, "CPU", true, 7),
            new Part(5, "RAM", true, 4),
            new Part(6, "Videocard", true, 2),
            new Part(7, "HDD", true, 8),
            new Part(8, "SSD", true, 6),
            new Part(9, "Cooler", true, 4),
            new Part(10, "Mouse", false, 8),
            new Part(11, "Keyboard", false, 5),
            new Part(12, "Monitor", true, 5),
            new Part(13, "Loudspeakers", false, 12),
            new Part(14, "CD-ROM", false, 7)));

    public Part getPartById(int id) {
        return mockPartList.get(--id);
    }

    public Part addPart(Part part) {
        int id = mockPartList.size();
        part.setId(++id);
        mockPartList.add(part);
        return mockPartList.get(--id);
    }

    public Part updatePartById(int id, Part part) {
        mockPartList.set(--id, part);
        return mockPartList.get(id);
    }

    public int deletePartById(int id) {
        Iterator<Part> iterator = mockPartList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
        return id;
    }

    public PartList getPartList(Integer page, Integer size, String search, String required) {
        PartList partList = new PartList();

        //can assembly comps number
        int num = findOutMaxCompsNumber(partList);
        partList.setCanAssemblyComps(num);

        //filter required
        List<Part> requiredParts = mockPartList;
        if (required != null && (required.equals(REQUIRED_TRUE) || required.equals(REQUIRED_FALSE))) {
            requiredParts = filterParts(required);
        }

        //search part
        List<Part> searchedParts = requiredParts;
        if (search != null && !search.isEmpty()) {
            searchedParts = searchForParts(search);
        }
        if (searchedParts.isEmpty()) {
            partList.setList(new ArrayList<>());
            return partList;
        }

        //pagination
        List<List<Part>> paginatedData = getPages(searchedParts, size);
        if (page == null || page < 1 || page > paginatedData.size()) {
            page = 1;
        }
        partList.setList(paginatedData.get(--page));
        return partList;
    }

    private int findOutMaxCompsNumber(PartList partList) {
        partList.setList(mockPartList);
        int maxCanAssemblyComps = Integer.MAX_VALUE;
        for (Part part : mockPartList) {
            if (part.isRequired() && part.getAmount() < maxCanAssemblyComps) {
                maxCanAssemblyComps = part.getAmount();
            }
        }
        return maxCanAssemblyComps == Integer.MAX_VALUE ? 0 : maxCanAssemblyComps;
    }

    private List<Part> filterParts(String required) {
        List<Part> resultList = new ArrayList<>();
        if (required.equals(REQUIRED_TRUE)) {
            for (Part part : mockPartList) {
                if (part.isRequired()){
                    resultList.add(part);
                }
            }
        } else {
            for (Part part : mockPartList) {
                if (!part.isRequired()){
                    resultList.add(part);
                }
            }
        }
        return resultList;
    }

    private static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (pageSize == null || pageSize < 1 || pageSize > mockPartList.size()) {
            pageSize = mockPartList.size();
        }
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<>(c);
        int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        List<List<T>> pages = new ArrayList<>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }

    private List<Part> searchForParts(String search) {
        List<Part> resultList = new ArrayList<>();
        for (Part part : mockPartList) {
            if (part.getName().toLowerCase().contains(search.toLowerCase())) {
                resultList.add(part);
            }
        }
        return resultList;
    }
}
