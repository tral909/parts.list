package ru.javarush.internship.parts.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javarush.internship.parts.list.model.Part;
import ru.javarush.internship.parts.list.service.PartService;

import java.util.List;

@Controller
@RequestMapping(value = "/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Part> getPartList() {
        return partService.getPartList();
    }
}
