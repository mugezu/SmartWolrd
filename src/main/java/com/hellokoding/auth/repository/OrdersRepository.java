package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 28.03.2018.
 */
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT  max (lo.id) FROM Orders lo")
    Long findByIdMax();

    List<Orders> findById(Long id);

    @Query("SELECT lo FROM Orders lo order by lo.status.id")
    List<Orders> findAllSort();

    @Query("SELECT lo FROM Orders lo where lo.idBayer=?1 order by lo.status.id")
    List<Orders> findAllByIdSort(User user);

}
