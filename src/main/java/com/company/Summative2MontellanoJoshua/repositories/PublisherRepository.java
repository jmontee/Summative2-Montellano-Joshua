package com.company.Summative2MontellanoJoshua.repositories;

import com.company.Summative2MontellanoJoshua.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
