package com.eric.data.repository;

import java.io.Serializable;

import com.eric.data.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface IBaseRepository<ENTITY extends IEntity<?>, ID extends Serializable> extends JpaRepository<ENTITY, ID>   {

}
