package com.hellokoding.auth.web;

import com.hellokoding.auth.Other.Filters;
import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.CatalogRepository;
import com.hellokoding.auth.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Роман on 19.02.2018.
 */
@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private HttpSession httpSession;

    private final String FILTERS = "filters";
    private final String LIST_CATALOG = "listCatalog";
    private final String CATALOG = "catalog";
    private final String AMOUNT_ALL_ITEM = "amountAllItem";
    private final String CURRENT_PAGE = "page";
    private final Integer AMOUNT_ITEM_PAGE = 2;
    private final String ITEM_PAGE = "itemPage";
    private Filters f = new Filters();

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String   showCatalog(@ModelAttribute("filters") Filters filters, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<Catalog> catalogList;
        Filters filters1 = (Filters) httpSession.getAttribute("filters");
        catalogList = catalogService.findItemFilter(filters1);
        List<Catalog> finalCatalogList = catalogService.findItemFromPage(page, AMOUNT_ITEM_PAGE, catalogList);

        model.addAttribute(AMOUNT_ALL_ITEM, catalogList.size());
        model.addAttribute(LIST_CATALOG, finalCatalogList);
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(ITEM_PAGE, AMOUNT_ITEM_PAGE);
        model.addAttribute("minPrice", catalogRepository.findOneByMinPrice());
        model.addAttribute("maxPrice", catalogRepository.findOneByMaxPrice());
        model.addAttribute("company", catalogRepository.findDistinctByCompany());
        model.addAttribute("displaySize", catalogRepository.findDistinctByDisplaySize());
        model.addAttribute("HDD", catalogRepository.findDistinctByHDD());
        model.addAttribute("OS", catalogRepository.findDistinctByOS());
        model.addAttribute("displayType", catalogRepository.findDistinctByDisplayType());
        model.addAttribute("RAM", catalogRepository.findDistinctByRAM());
        return CATALOG;
    }

    @RequestMapping(value = "/filters", method = RequestMethod.GET)
    public String filters(@ModelAttribute("filters") Filters filters) {
        httpSession.setAttribute(FILTERS, filters);
        return "redirect:catalog";
    }

    @RequestMapping(value = "/filtersClean", method = RequestMethod.GET)
    public String filtersClean(Model model) {
        httpSession.setAttribute(FILTERS, null);
        return "redirect:catalog";
    }


}
