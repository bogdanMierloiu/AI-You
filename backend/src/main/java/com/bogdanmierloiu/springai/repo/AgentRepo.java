package com.bogdanmierloiu.springai.repo;

import com.bogdanmierloiu.springai.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long> {

    Optional<Agent> findByUuid(UUID agentUuid);
}
