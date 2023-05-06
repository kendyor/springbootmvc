package com.certus.plataformasweb.demoweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.certus.plataformasweb.demoweb.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
