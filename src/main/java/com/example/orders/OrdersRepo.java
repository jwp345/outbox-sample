package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<CustomerOrder, Integer> {
}
