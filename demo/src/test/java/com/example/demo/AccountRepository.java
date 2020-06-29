package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findById(long id);
  List<Account> findByNameStartingWith(String name);
  List<Account> findByBalanceBetween(int low, int high);
}
