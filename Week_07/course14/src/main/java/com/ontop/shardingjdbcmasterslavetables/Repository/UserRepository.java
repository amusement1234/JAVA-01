package com.ontop.shardingjdbcmasterslavetables.Repository;

import com.ontop.shardingjdbcmasterslavetables.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


}
