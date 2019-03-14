package com.chenhao.service.impl;

import com.chenhao.entity.UserDTO;
import com.chenhao.mapper.UserDao;
import com.chenhao.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static Logger logger=Logger.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;
    @Override
    public List<UserDTO> findAll() {
        logger.info("----findAll----");
        return userDao.findAll();
    }

    @Override
    public UserDTO findById(String id) {
        logger.info("----findById----id::"+id);
        return userDao.findById(id);
    }
    @Transactional
    @Override
    public void insert(UserDTO user) {
        logger.info("----insert----user::"+user.toString());
        userDao.insert(user);
    }
    @Transactional
    @Override
    public void update(UserDTO user) {
        logger.info("----update----user::"+user.toString());
        userDao.update(user);
    }

    @Override
    public void deleteOne(String id) {
        logger.info("----deleteOne----id::"+id);
        userDao.deleteOne(id);
    }
}
