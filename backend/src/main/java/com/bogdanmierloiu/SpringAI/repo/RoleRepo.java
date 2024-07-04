package com.bogdanmierloiu.SpringAI.VectorAPI.repo;

import com.bogdanmierloiu.SpringAI.VectorAPI.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
