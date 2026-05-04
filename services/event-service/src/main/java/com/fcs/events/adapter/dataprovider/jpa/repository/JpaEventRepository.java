package com.fcs.events.adapter.dataprovider.jpa.repository;

import com.fcs.events.adapter.dataprovider.jpa.entity.JpaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEventRepository extends JpaRepository<JpaEvent, Integer> {

    Optional<JpaEvent> findByName(String name);
}
