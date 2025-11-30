package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Majalah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajalahRepository extends JpaRepository<Majalah, String> { //
}