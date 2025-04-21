package com.uds.master_isok.teacher;

import com.uds.master_isok.utils.payload.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface teacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE " +
            "(COALESCE(:search, '') = '' OR " +
            "LOWER(CONCAT(t.firstName, ' ', t.lastName)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Teacher> searchTeachers(
            @Param("search") String searchFilter,
            Pageable pageable
    );

    default Page<Teacher> findAllPaginated(String search, int page, int size, String... sort) {
        return searchTeachers(
                StringUtils.hasText(search) ? search.trim().toLowerCase() : null,
                PageRequest.of(page, size, buildSort(sort))
        );
    }

    private Sort buildSort(String... sortFields) {
        return Sort.by(Arrays.stream(sortFields)
                .map(field -> {
                    if (field.startsWith("-")) {
                        return Sort.Order.desc(field.substring(1));
                    }
                    return Sort.Order.asc(field);
                })
                .collect(Collectors.toList()));
    }
}
