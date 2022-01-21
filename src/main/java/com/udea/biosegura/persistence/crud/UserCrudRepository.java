package com.udea.biosegura.persistence.crud;

import com.udea.biosegura.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, String> {
}
