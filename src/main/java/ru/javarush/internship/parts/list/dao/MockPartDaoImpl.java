package ru.javarush.internship.parts.list.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Repository("mockDao")
public class MockPartDaoImpl implements PartDao {

    private static List<Part> mockPartsList = new ArrayList<>(Arrays.asList(
            new Part(0L, "Motherboard", true, 3),
            new Part(1L, "Soundboard", false, 2),
            new Part(2L, "Network adapter", false, 5),
            new Part(3L, "CPU", true, 7),
            new Part(4L, "RAM", true, 4),
            new Part(5L, "Videocard", true, 2),
            new Part(6L, "HDD", true, 8),
            new Part(7L, "SSD", true, 6),
            new Part(8L, "Cooler", true, 4),
            new Part(9L, "Mouse", false, 8),
            new Part(10L, "Keyboard", false, 5),
            new Part(11L, "Monitor", true, 5),
            new Part(12L, "Loudspeakers", false, 12),
            new Part(13L, "CD-ROM", false, 7),
            new Part(14L, "Motherboard2", true, 4),
            new Part(15L, "Soundboard2", false, 2),
            new Part(16L, "Network adapter2", false, 5),
            new Part(17L, "CPU2", true, 7),
            new Part(18L, "RAM2", true, 4),
            new Part(19L, "Videocard2", true, 2),
            new Part(20L, "HDD2", true, 8),
            new Part(21L, "SSD2", true, 6),
            new Part(22L, "Cooler2", true, 4),
            new Part(23L, "Mouse2", false, 8),
            new Part(24L, "Keyboard2", false, 5),
            new Part(25L, "Monitor2", true, 5),
            new Part(26L, "Loudspeakers2", false, 12),
            new Part(27L, "CD-ROM2", false, 7)));

    public Part getPartById(Long id) {
        return mockPartsList.get(id.intValue());
    }

    public Part addPart(Part part) {
        int id = mockPartsList.size();
        part.setId((long) id);
        mockPartsList.add(part);
        return mockPartsList.get(id);
    }

    public Part updatePartById(Long id, Part part) {
        mockPartsList.set(id.intValue(), part);
        return mockPartsList.get(id.intValue());
    }

    public void deletePartById(Long id) {
        Iterator<Part> iterator = mockPartsList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                while (iterator.hasNext()) {
                    Part part = iterator.next();
                    part.setId(part.getId() - 1);
                }
            }
        }
    }

    public PartList getPartsList(Integer page, Integer size, String search, Boolean required) {
        PartList resultList = new PartList();

        List<Part> clonedList = new ArrayList<>();
        try {
            for (Part part : mockPartsList) {
                clonedList.add((Part) part.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //can assembly comps number
        int num = findOutMaxCompsNumber(resultList);
        resultList.setCanAssemblyComps(num);

        //filter required
        List<Part> filterList = new ArrayList<>();
        if (required != null) {
            fetchFilteredParts(required, filterList);
        } else {
            filterList = mockPartsList;
        }

        //search part
        List<Part> searchList = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
             fetchSearchedParts(search, searchList);
        } else {
            searchList = mockPartsList;
        }

        //intersection
        if (filterList != mockPartsList) clonedList.retainAll(filterList);
        if (searchList != mockPartsList) clonedList.retainAll(searchList);

        //pagination
        List<List<Part>> paginatedData = getPages(clonedList, size);
        if (page == null || page < 1 || page > paginatedData.size()) {
            page = 1;
        }
        if (paginatedData.isEmpty()) {
            resultList.setList(new ArrayList<>());
        } else {
            resultList.setList(paginatedData.get(--page));
        }
        return resultList;
    }

    private int findOutMaxCompsNumber(PartList partList) {
        partList.setList(mockPartsList);
        int maxCanAssemblyComps = Integer.MAX_VALUE;
        for (Part part : mockPartsList) {
            if (part.isRequired() && part.getAmount() < maxCanAssemblyComps) {
                maxCanAssemblyComps = part.getAmount();
            }
        }
        return maxCanAssemblyComps == Integer.MAX_VALUE ? 0 : maxCanAssemblyComps;
    }

    private void fetchFilteredParts(Boolean required, List<Part> resultList) {
        for (Part part : mockPartsList) {
            if (required == part.isRequired()){
                resultList.add(part);
            }
        }
    }

    private void fetchSearchedParts(String search, List<Part> searchList) {
        for (Part part : mockPartsList) {
            if (part.getName().toLowerCase().contains(search.toLowerCase())) {
                searchList.add(part);
            }
        }
    }

    private static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (pageSize == null || pageSize < 1 || pageSize > mockPartsList.size()) {
            pageSize = mockPartsList.size();
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
