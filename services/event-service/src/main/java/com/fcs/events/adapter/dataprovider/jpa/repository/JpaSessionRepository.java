package com.fcs.events.adapter.dataprovider.jpa.repository;

import com.fcs.events.adapter.dataprovider.jpa.entity.JpaSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSessionRepository extends JpaRepository<JpaSession, Integer> {

    List<JpaSession> findByEventId(Integer eventId);
}