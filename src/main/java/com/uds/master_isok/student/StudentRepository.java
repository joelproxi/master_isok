package com.uds.master_isok.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmailIgnoreCase(String email);

    @Query("SELECT t FROM Student t WHERE " +
            "(COALESCE(:search, '') = '' OR " +
            "LOWER(CONCAT(t.firstName, ' ', t.lastName)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Student> searchStudents(
            @Param("search") String searchFilter,
            Pageable pageable
    );

    default Page<Student> findAllPaginated(String search, int page, int size, String... sort) {
        return searchStudents(
                StringUtils.hasText(search) ? search.trim().toLowerCase() : null,
                PageRequest.of(
                        page,
                        size,
                        parseSort(sort != null ? sort : new String[0])
                )
        );
    }


    private Sort parseSort(String... sortFields) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortFields != null) {
            for (String field : sortFields) {
                if (field == null || field.isBlank()) continue;

                String[] parts = field.split(",");
                String property = parts[0].trim();

                if (property.isEmpty()) continue;

                Sort.Direction direction = parts.length > 1
                        ? Sort.Direction.fromString(parts[1].trim())
                        : Sort.Direction.ASC;

                orders.add(new Sort.Order(direction, property));
            }
        }

        return orders.isEmpty()
                ? Sort.by("lastName").ascending()
                : Sort.by(orders);
    }
}
