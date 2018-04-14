package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 19.02.2018.
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long>, JpaSpecificationExecutor<Catalog> {
    List<Catalog> findAll();

    Catalog findAllById(Long id);

    //  List<Catalog> findAllByRAM(Integer company);
    //   List<String> findDistinctByCompanyOrderByCompany();


    @Query("SELECT min(price) FROM Catalog")
    Integer findOneByMinPrice();

    @Query("SELECT max(price) FROM Catalog")
    Integer findOneByMaxPrice();

    @Query("select distinct company from Catalog")
    List<String> findDistinctByCompany();

    @Query("select distinct OS from Catalog")
    List<String> findDistinctByOS();

    @Query("select distinct displaySize from Catalog")
    List<String> findDistinctByDisplaySize();

    @Query("select distinct displayType from Catalog")
    List<String> findDistinctByDisplayType();

    @Query("select distinct RAM from Catalog")
    List<String> findDistinctByRAM();

    @Query("select distinct HDD from Catalog")
    List<String> findDistinctByHDD();
   /* @Query(value = "select c from Catalog c where (concat('%', :filters, '%') is NULL or c.RAM like concat('%', :filters, '%'))")
    List<Catalog> findByFilters(@Param("filters") Integer filters);
*/

}
