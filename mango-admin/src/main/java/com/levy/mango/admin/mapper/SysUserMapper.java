package com.levy.mango.admin.mapper;

import com.levy.mango.admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysUserMapper {

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    SysUser findByName(@Param(value = "username") String username);
    /**
     * 分页查询
     * @return
     */
    List<SysUser> findPage();
    /**
     * 查询全部
     */
    List<SysUser> findAll();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int updateByPrimaryKey(SysUser record);


}