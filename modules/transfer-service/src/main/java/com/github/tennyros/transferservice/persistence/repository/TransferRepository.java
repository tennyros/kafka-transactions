package com.github.tennyros.transferservice.persistence.repository;

import com.github.tennyros.transferservice.persistence.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
}
