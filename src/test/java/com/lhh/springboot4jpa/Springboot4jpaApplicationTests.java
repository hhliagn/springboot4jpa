package com.lhh.springboot4jpa;

import com.lhh.Springboot4jpaApplication;
import com.lhh.dao.UserDao;
import com.lhh.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springboot4jpaApplication.class)
public class Springboot4jpaApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void test(){
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

	@Test
	public void contextLoads() {
	}

}
