package com.hellokoding.auth.service;

import com.hellokoding.auth.Other.Filters;
import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.repository.CatalogRepository;
import com.hellokoding.auth.repository.CatalogSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 08.04.2018.
 */
@Service
public class CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;


    public Catalog findItem(Long id) {
        return catalogRepository.findAllById(id);
    }

    public void addItem(Catalog item) throws IOException {
        InputStream is = item.getFile().getInputStream();

        int i = is.available();
        byte[] b = new byte[i];
        is.read(b);
        item.setPicture(b);

        catalogRepository.saveAndFlush(item);
    }

    public List<Catalog> findItemFilter(Filters filters) {
        if (filters == null) {
            filters = new Filters();
        }
        return new ArrayList<>(catalogRepository.findAll(Specifications.where(CatalogSpecs.priceMinMax(filters))));


    }

    public List<Catalog> findItemFromPage(Integer page, Integer amount, List<Catalog> catalogList) {
        int p = page;
        List<Catalog> finalListCatalog = new ArrayList<>(20);
        try {
            for (int i = (p - 1) * amount; i < p * amount; i++) {
                finalListCatalog.add(catalogList.get(i));
            }
        } catch (Exception e) {

        } finally {
            return finalListCatalog;
        }
    }


    public void deleteItem(Long catalog) {
        catalogRepository.delete(catalog);
    }
}