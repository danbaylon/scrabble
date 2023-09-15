package com.palo.it.repository;

import com.palo.it.model.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculatorRepository extends JpaRepository<Entry, Long> {

    List<Entry> findAll();
    Entry findByWord(String word);
}
