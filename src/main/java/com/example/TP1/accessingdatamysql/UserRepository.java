package com.example.TP1.accessingdatamysql;

import com.example.TP1.Entite.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
