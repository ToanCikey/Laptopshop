package vn.edu.stu.laptopshop.specification;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.stu.laptopshop.controller.request.product.PriceRangeRequest;
import vn.edu.stu.laptopshop.model.BrandEntity;
import vn.edu.stu.laptopshop.model.CategoryEntity;
import vn.edu.stu.laptopshop.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<ProductEntity> findByKeyWord(String keyword) {
            return (root, query, cb) -> {
                if (keyword == null || keyword.isEmpty()) return cb.conjunction();
                String likePattern = "%" + keyword + "%";
                
                return cb.like(root.get("name"), likePattern);
            };
        }

    public static Specification<ProductEntity> filterByBrandNames(List<String> brandNames) {
        return (root, query, cb) -> {
            if (brandNames == null || brandNames.isEmpty()) return cb.conjunction();
            Join<ProductEntity, BrandEntity> brandJoin = root.join("brand", JoinType.INNER);
            Expression<String> brandNameExpr = cb.lower(brandJoin.get("name"));
            return brandNameExpr.in(brandNames.stream().map(String::toLowerCase).toList());
        };
    }

    public static Specification<ProductEntity> filterByCategoryNames(List<String> categoryNames) {
        return (root, query, cb) -> {
            if (categoryNames == null || categoryNames.isEmpty()) return cb.conjunction();
            Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category", JoinType.INNER);
            Expression<String> categoryNameExpr = cb.lower(categoryJoin.get("name"));
            return categoryNameExpr.in(categoryNames.stream().map(String::toLowerCase).toList());
        };
    }
    public static Specification<ProductEntity> filterByMultiplePriceRanges(List<PriceRangeRequest> priceRanges) {
        return (root, query, cb) -> {
            if (priceRanges == null || priceRanges.isEmpty()) {
                return cb.conjunction();
            }

            List<Predicate> rangePredicates = new ArrayList<>();
            for (PriceRangeRequest range : priceRanges) {
                Predicate predicate = cb.between(root.get("price"), range.getMin(), range.getMax());
                rangePredicates.add(predicate);
            }

            return cb.or(rangePredicates.toArray(new Predicate[0]));
        };
    }
}
