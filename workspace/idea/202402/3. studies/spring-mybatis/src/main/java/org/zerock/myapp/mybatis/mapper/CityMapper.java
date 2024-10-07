package org.zerock.myapp.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.City;


@Mapper
public interface CityMapper {

    // 1st. sample
    @Select("SELECT * FROM city WHERE state = #{state}")
    City findByState1(@Param("state") String state);

    // 2nd. sample - Using Mapper XML file.
    City findByState2(@Param("id") Integer id);

} // end interface
