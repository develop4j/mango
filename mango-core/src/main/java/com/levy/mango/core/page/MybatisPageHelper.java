package com.levy.mango.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.levy.mango.common.utils.ReflectionUtils;

import java.util.List;

/**
 * 分页查询助手
 * @author levy
 * @date Jan 17,2020
 */
public class MybatisPageHelper {
    public static final String findPage = "findPage";

    /**
     * 分页查询，约定查询方法名为：findPage
     * @param pageRequest
     * @param mapper
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper){
        return findPage(pageRequest,mapper,findPage);
    }

    /**
     * 调用分页插件进行分页查询
     * @param pageRequest
     * @param mapper
     * @param queryMethodName
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName,Object... args) {
        // 设置分页数据
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        // 利用反射调用查询方法
        Object result = ReflectionUtils.invoke(mapper,queryMethodName,args);
        return getPageResult(pageRequest,new PageInfo<List>((List) result));
    }

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<List> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
