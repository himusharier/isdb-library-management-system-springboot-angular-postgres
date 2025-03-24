package org.himusharier.librarymanagementsystem.repository;

import org.himusharier.librarymanagementsystem.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
