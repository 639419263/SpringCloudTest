package com.chenhao.mapper;

import com.chenhao.entity.UserDTO;

import java.util.List;

public interface UserDao {
    /**
     *  查询全部
     * @return
     */
    public List<UserDTO> findAll();

    /**
     *  查询详情
     * @param id
     * @return
     */
    public UserDTO findById(String id);

    /**
     *   新增
     * @param user
     */
    public void insert(UserDTO user);

    /**
     *  修改
     * @param user
     */
    public void update(UserDTO user);

    /**
     *  删除
     * @param id
     */
    public void deleteOne(String id);
}
