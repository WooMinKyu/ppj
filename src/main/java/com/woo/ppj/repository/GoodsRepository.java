package com.woo.ppj.repository;


import com.woo.ppj.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findByNameContaining(String searchText);

    List<Goods> findByCategory(String category);
}
