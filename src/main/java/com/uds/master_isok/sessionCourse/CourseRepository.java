package com.uds.master_isok.sessionCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public interface CourseRepository extends JpaRepository<Courses, Long> {

    @Query("select c from Courses c where (coalesce(:search, '') = '' or lower(concat(c.startTime, ' ', c.endTime)) like lower(concat('%', :search, '%')) or lower(c.ue.title) like lower(concat('%', :search, '%')) or lower(concat(c.teacher.firstName, ' ', c.teacher.lastName)) like lower(concat('%', :search, '%')))")
    Page<Courses> searchCourses(
            @Param("search") String searchFilter,
            Pageable pageable);

    default Page<Courses> findAllPaginated(
            String search, int page, int size, String... sort
    ){
        return searchCourses(
                StringUtils.hasText(search) ? search.trim().toLowerCase() : null,
                PageRequest.of(page, size, parseSort())
        );
    }

    private Sort parseSort(String... sortFields){
        List<Sort.Order> orders = new ArrayList<>();

        if(sortFields != null){
            for(String field : sortFields){
                if(field == null || field.isBlank()) continue;

                String[] parts = field.split(",");
                String property = parts[0].trim();

                if(property.isEmpty()) continue;

                Sort.Direction direction = parts.length > 1
                        ? Sort.Direction.fromString(parts[1].trim())
                        : Sort.Direction.ASC;
                orders.add(new Sort.Order(direction, property));
            }
        }
        return orders.isEmpty()
                ? Sort.by("sessionDate").ascending()
                : Sort.by(orders);
    }


}
