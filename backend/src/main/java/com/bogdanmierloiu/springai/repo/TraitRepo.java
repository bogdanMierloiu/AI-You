package com.bogdanmierloiu.springai.repo;

import com.bogdanmierloiu.springai.entity.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitRepo extends JpaRepository<Trait, Long> {
}
