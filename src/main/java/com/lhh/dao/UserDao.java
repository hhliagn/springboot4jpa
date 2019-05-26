package com.lhh.dao;

import com.lhh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lhh
 * @date 2019/5/26 11:49
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

}
