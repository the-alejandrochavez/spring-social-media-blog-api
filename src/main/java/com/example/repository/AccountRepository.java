package com.example.repository;

// import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    Account findByUsernameAndPassword(String username, String password);

    @Query("FROM Account WHERE account_id = :id")
    Account findByAccountId(@Param("id") Integer account_id);

}
