package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.iotstar.entity.CategoryEntity;

/**
 * Thay the cho ICategoryDao + CategoryDaoImpl + ICategoryService + CategoryServiceImpl
 * cua ban cu. JpaRepository da cung cap san: save(), findById(), findAll(), deleteById()...
 * Chi can khai bao them ham tim kiem theo ten co phan trang.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    // keyword = "" se khop voi LIKE '%%' => tra ve tat ca (khong loc)
    @Query("SELECT c FROM CategoryEntity c " +
           "WHERE LOWER(c.categoryname) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY c.categoryid DESC")
    Page<CategoryEntity> searchByName(@Param("keyword") String keyword, Pageable pageable);
}
