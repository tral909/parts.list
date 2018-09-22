package ru.javarush.internship.parts.list.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.*;

@Repository("mockDao")
public class MockPartDaoImpl implements PartDao {
    private static final String REQUIRED_TRUE = "true";
    private static final String REQUIRED_FALSE = "false";

    private static List<Part> mockPartList = new ArrayList<>(Arrays.asList(
            new Part(0, "Motherboard", true, 3),
            new Part(1, "Soundboard", false, 2),
            new Part(2, "Network adapter", false, 5),
            new Part(3, "CPU", true, 7),
            new Part(4, "RAM", true, 4),
            new Part(5, "Videocard", true, 2),
            new Part(6, "HDD", true, 8),
            new Part(7, "SSD", true, 6),
            new Part(8, "Cooler", true, 4),
            new Part(9, "Mouse", false, 8),
            new Part(10, "Keyboard", false, 5),
            new Part(11, "Monitor", true, 5),
            new Part(12, "Loudspeakers", false, 12),
            new Part(13, "CD-ROM", false, 7),
            new Part(14, "Motherboard2", true, 4),
            new Part(15, "Soundboard2", false, 2),
            new Part(16, "Network adapter2", false, 5),
            new Part(17, "CPU2", true, 7),
            new Part(18, "RAM2", true, 4),
            new Part(19, "Videocard2", true, 2),
            new Part(20, "HDD2", true, 8),
            new Part(21, "SSD2", true, 6),
            new Part(22, "Cooler2", true, 4),
            new Part(23, "Mouse2", false, 8),
            new Part(24, "Keyboard2", false, 5),
            new Part(25, "Monitor2", true, 5),
            new Part(26, "Loudspeakers2", false, 12),
            new Part(27, "CD-ROM2", false, 7)));

    public Part getPartById(int id) {
        return mockPartList.get(id);
    }

    public Part addPart(Part part) {
        int id = mockPartList.size();
        part.setId(id);
        mockPartList.add(part);
        return mockPartList.get(id);
    }

    public Part updatePartById(int id, Part part) {
        mockPartList.set(id, part);
        return mockPartList.get(id);
    }

    public void deletePartById(int id) {
        Iterator<Part> iterator = mockPartList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                while (iterator.hasNext()) {
                    Part part = iterator.next();
                    part.setId(part.getId() - 1);
                }
            }
        }
    }

    public PartList getPartList(Integer page, Integer size, String search, String required) {
        PartList partList = new PartList();

        List<Part> resultList = new ArrayList<>();
        try {
            for (Part part : mockPartList) {
                resultList.add((Part) part.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //can assembly comps number
        int num = findOutMaxCompsNumber(partList);
        partList.setCanAssemblyComps(num);

        //filter required
        List<Part> filterList = new ArrayList<>();
        if (required != null && !required.isEmpty() && (required.equals(REQUIRED_TRUE) || required.equals(REQUIRED_FALSE))) {
            fetchFilteredParts(required, filterList);
        } else {
            filterList = mockPartList;
        }

        //search part
        List<Part> searchList = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
             fetchSearchedParts(search, searchList);
        } else {
            searchList = mockPartList;
        }

        //intersection
        if (filterList != mockPartList) resultList.retainAll(filterList);
        if (searchList != mockPartList) resultList.retainAll(searchList);

        //pagination
        List<List<Part>> paginatedData = getPages(resultList, size);
        if (page == null || page < 1 || page > paginatedData.size()) {
            page = 1;
        }
        if (paginatedData.isEmpty()) {
            partList.setList(new ArrayList<>());
        } else {
            partList.setList(paginatedData.get(--page));
        }
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

    private void fetchFilteredParts(String required, List<Part> resultList) {
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
    }

    private void fetchSearchedParts(String search, List<Part> searchList) {
        for (Part part : mockPartList) {
            if (part.getName().toLowerCase().contains(search.toLowerCase())) {
                searchList.add(part);
            }
        }
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
}
