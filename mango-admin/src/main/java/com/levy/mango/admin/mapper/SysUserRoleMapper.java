package com.levy.mango.admin.mapper;

import com.levy.mango.admin.model.SysUserRole;

public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int insertSelective(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    SysUserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int updateByPrimaryKeySelective(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Jan 16 20:41:11 CST 2020
     */
    int updateByPrimaryKey(SysUserRole record);
}