package com.myprojects.invoice.repositories;

import com.myprojects.invoice.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    @Override
    List<Users> findAll();

    @Override
    Optional<Users> findById(Long userId);

    @Override
    Users save(Users user);

    @Override
    void deleteById(Long userId);
}