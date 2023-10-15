package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Long> {
}
