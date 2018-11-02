package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    public Employee search(Integer id);
}
