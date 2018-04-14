package com.hellokoding.auth.repository;

import com.hellokoding.auth.Other.Filters;
import com.hellokoding.auth.model.Catalog;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Роман on 21.02.2018.
 */
public class CatalogSpecs {

    public static Specification<Catalog> priceMinMax(final Filters filters) {
        return new Specification<Catalog>() {
            @Override
            public Predicate toPredicate(Root<Catalog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                try {
                    query.where(new CatalogSpecs().filters(root, query, cb, filters));
                    query.orderBy(cb.asc(root.get("price"))).getRestriction();
                } catch (Exception e) {
                    return null;
                }
                return query.getRestriction();
            }
        };
    }

    public static Specification<Catalog> priceMaxMin(final Filters filters) {
        return new Specification<Catalog>() {
            @Override
            public Predicate toPredicate(Root<Catalog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                try {
                    query.where(new CatalogSpecs().filters(root, query, cb, filters));
                    query.orderBy(cb.desc(root.get("price"))).getRestriction();
                } catch (Exception e) {
                    return null;
                }
                return query.getRestriction();
            }
        };

    }

    public final Predicate filters(Root<Catalog> root, CriteriaQuery<?> query, CriteriaBuilder cb, Filters filters) {
        Predicate result = cb.conjunction();
        Predicate p;
        try {
            if (filters.getHDD() != null) {
                p = cb.equal(root.get("HDD"), filters.getHDD());
                result = cb.and(result, p);
            }
            if (filters.getRAM() != null) {
                p = cb.equal(root.get("RAM"), filters.getRAM());
                result = cb.and(result, p);
            }
            if (filters.getCore() != null) {
                p = cb.equal(root.get("core"), filters.getCore());
                result = cb.and(result, p);
            }
            if (filters.getDisplaySize() != null) {
                p = cb.equal(root.get("displaySize"), filters.getDisplaySize());
                result = cb.and(result, p);
            }
            if (filters.getDisplayType() != null) {
                p = cb.equal(root.get("displayType"), filters.getDisplayType());
                result = cb.and(result, p);
            }
            if (filters.getCompany() != null) {
                p = cb.equal(root.get("company"), filters.getCompany());
                result = cb.and(result, p);
            }
            if (filters.getPriceMax() != null) {
                p = cb.le(root.<Integer>get("price"), filters.getPriceMax());
                result = cb.and(result, p);
            }
            if (filters.getPriceMin() != null) {
                p = cb.ge(root.<Integer>get("price"), filters.getPriceMin());
                result = cb.and(result, p);
            }
            if (filters.getProcessor() != null) {
                p = cb.equal(root.get("processor"), filters.getProcessor());
                result = cb.and(result, p);
            }
            if (filters.getOS() != null) {
                p = cb.equal(root.get("OS"), filters.getOS());
                result = cb.and(result, p);
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }
};
