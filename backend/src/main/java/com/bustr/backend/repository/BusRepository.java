package com.bustr.backend.repository;

import com.bustr.backend.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, String> {

}
