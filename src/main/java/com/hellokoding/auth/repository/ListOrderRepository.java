package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.ListOrder;
import com.hellokoding.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Роман on 28.03.2018.
 */
public interface ListOrderRepository extends JpaRepository<ListOrder, Long> {
    @Query("SELECT coalesce(max(lo.idOrder), 0) FROM ListOrder lo")
    Long findOneMaxByIdOrder();

    List<ListOrder> findByIdOrder(Long id);

    @Query("SELECT lo FROM ListOrder lo order by lo.status.id")
    List<ListOrder> findAllSort();

    @Query("SELECT lo FROM ListOrder lo where lo.idBayer=?1 order by lo.status.id")
    List<ListOrder> findAllByIdSort(User user);

}
