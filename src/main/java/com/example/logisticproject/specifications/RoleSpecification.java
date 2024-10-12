package com.example.logisticproject.specifications;

import com.example.logisticproject.dto.req.RoleRequest;
import com.example.logisticproject.entity.auth.Role;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class RoleSpecification implements Specification<Role> {

    private final RoleRequest request;
    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!Objects.isNull(request.getName())) {
            predicates.add(builder.like(builder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
        }
        if (!Objects.isNull(request.getCode())) {
            predicates.add(builder.like(builder.lower(root.get("code")), "%" + request.getCode() + "%"));
        }

        predicates.add(builder.equal(root.get("deleted"), false));
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
