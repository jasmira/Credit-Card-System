package com.assignment.creditcardsystem.repository;

import com.assignment.creditcardsystem.entity.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CreditCard, Long> {
}
