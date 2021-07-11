package ru.javarush.internship.parts.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.dto.PartListDto;
import ru.javarush.internship.parts.list.service.PartService;

@RestController
@RequestMapping(value = "/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @RequestMapping(method = RequestMethod.GET)
    public PartListDto filterParts(@RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String search,
                                   @RequestParam(required = false) Boolean required) {
        return partService.filterParts(page, size, search, required);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Part getPartById(@PathVariable("id") Long id) {
        return partService.getPartById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Part addPart(@RequestBody Part part) {
        return partService.addPart(part);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Part updatePart(@PathVariable("id") Long id, @RequestBody Part part) {
        return partService.updatePartById(id, part);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePart(@PathVariable("id") Long id) {
        partService.deletePartById(id);
    }
}
