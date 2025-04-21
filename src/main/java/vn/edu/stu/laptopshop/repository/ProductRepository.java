package vn.edu.stu.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.stu.laptopshop.model.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    @Query("SELECT u FROM ProductEntity u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.brand.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(u.price AS string) LIKE CONCAT('%', :keyword, '%')")
    List<ProductEntity> getAllBySearch(String keyword);

    Optional<ProductEntity> findByName(String name);
}
