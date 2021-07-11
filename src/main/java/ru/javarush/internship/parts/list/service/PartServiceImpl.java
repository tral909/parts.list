package ru.javarush.internship.parts.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.internship.parts.list.dao.PartDao;
import ru.javarush.internship.parts.list.dto.PartDto;
import ru.javarush.internship.parts.list.dto.PartListDto;
import ru.javarush.internship.parts.list.model.Part;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    @Autowired
    @Qualifier("databaseDao")
    private PartDao partDao;

    @Transactional(readOnly = true)
    public Part getPartById(Long id) {
        return partDao.getPartById(id);
    }

    public Part addPart(Part part) {
        return partDao.addPart(part);
    }

    public Part updatePartById(Long id, Part part) {
        return partDao.updatePartById(id, part);
    }

    public void deletePartById(Long id) {
        partDao.deletePartById(id);
    }

    @Transactional(readOnly = true)
    public PartListDto filterParts(Integer page, Integer size, String search, Boolean required) {
        if (search != null) {
            search = search.trim();
        }

        List<PartDto> partDtos = partDao.filterParts(page, size, search, required).stream()
                .map(p -> new PartDto(p.getId(), p.getName(), p.isRequired(), p.getAmount()))
                .collect(Collectors.toList());

        int comps = partDao.canAssemblyComps();
        return new PartListDto(partDtos, comps);
    }
}
