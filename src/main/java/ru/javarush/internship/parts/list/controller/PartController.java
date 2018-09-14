package ru.javarush.internship.parts.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.model.PartList;
import ru.javarush.internship.parts.list.service.PartService;

@RestController
@RequestMapping(value = "/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @RequestMapping(method = RequestMethod.GET)
    public PartList getPartList(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(required = false) String search) {
        return partService.getPartList(page, size, search);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Part getPartById(@PathVariable("id") int id) {
        return partService.getPartById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Part addPart(@RequestBody Part part) {
        return partService.addPart(part);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Part updatePart(@PathVariable("id") int id, @RequestBody Part part) {
        if (part.getId() != id) {
            part.setId(id);
        }
        return partService.updatePartById(id, part);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deletePart(@PathVariable("id") int id) {
        return partService.deletePartById(id);
    }
}
