package com.hellokoding.auth.web;

import com.hellokoding.auth.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Роман on 01.03.2018.
 */
@RestController
@RequestMapping("/catalog")
public class JsonControllerFromCatalog {
    @Autowired
    private CatalogRepository catalogRepository;

    @RequestMapping(value = "/company")
    public Iterable<String> getCompany(Model model) {
        List<String> list = catalogRepository.findDistinctByCompany();
        model.addAttribute("company", list);
        return list;
    }

    @RequestMapping(value = "/HDD")
    public Iterable<String> getHDD() {
        return catalogRepository.findDistinctByHDD();
    }

    @RequestMapping(value = "/displaySize")
    public Iterable<String> getDisplaySize() {
        return catalogRepository.findDistinctByDisplaySize();
    }

    @RequestMapping(value = "/displayType")
    public Iterable<String> getDisplayType() {
        return catalogRepository.findDistinctByDisplayType();
    }

    @RequestMapping(value = "/RAM")
    public Iterable<String> getRAM() {
        return catalogRepository.findDistinctByRAM();
    }

    @RequestMapping(value = "/OS")
    public Iterable<String> getOS() {
        return catalogRepository.findDistinctByOS();
    }

}
