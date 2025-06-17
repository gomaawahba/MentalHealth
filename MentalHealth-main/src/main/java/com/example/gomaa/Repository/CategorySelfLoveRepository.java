package com.example.gomaa.Repository;

import com.example.gomaa.entity.CategorySelfLove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategorySelfLoveRepository extends JpaRepository<CategorySelfLove,Long> {
}
