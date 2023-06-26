package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepo extends JpaRepository<Outbox, Integer> {
}
