package com.lhh.service;

import com.lhh.dao.UserDao;
import com.lhh.entity.User;
import com.lhh.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lhh
 * @date 2019/5/26 16:06
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findOne(Integer id){
        return userDao.findById(id).get();
    }

    public void add(User user){
        user.setId((int) idWorker.nextId());
        userDao.save(user);
    }

    public void update(User user){
        userDao.save(user);
    }

    public void deleteById(Integer id){
        userDao.deleteById(id);
    }

    /**
     * 创建Specification对象
     */
    private Specification<User> createSpecification(Map searchMap){
        //通常提供Specification接口的匿名实现类对象
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //1.准备List集合，存储Predicate条件对象
                List<Predicate> preList = new ArrayList<Predicate>();

                //2.根据用户输入的条件，构建Predicate条件，把条件放入List集合（***）
                //name
                if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                    // sql: Username like '%Java%'
                    preList.add( cb.like( root.get("name").as(String.class)  , "%"+searchMap.get("name")+"%"  ));
                }
                //id
                if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                    // sql: state = '1'
                    preList.add( cb.equal( root.get("id").as(String.class)  , searchMap.get("id") ) );
                }

                //3.使用连接条件把条件对象进行连接: Username like '%Java%' and state = '1' and count = 10

                //preList.toArray(preArray): 从preList集合中取出每个元素，逐个放入preArray数组里面，返回preArray数组
                Predicate[] preArray = new Predicate[preList.size()];
                return cb.and(preList.toArray(preArray));
            }
        };
    }

    /**
     * 条件查询
     */
    public List<User> findSearch(Map searchMap){
        //创建Specification对象
        Specification<User> spec = createSpecification(searchMap);
        return userDao.findAll(spec);
    }

    /**
     * 带条件的分页查询
     */
    public Page<User> findSearch(Map searchMap, int page, int size){
        //创建Specification对象
        Specification<User> spec = createSpecification(searchMap);
        //注意：PageRequest的page参数从0开始计算
        return userDao.findAll(spec,PageRequest.of(page-1,size));
    }
}
