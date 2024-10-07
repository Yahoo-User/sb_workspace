package org.zerock.myapp.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;


@Mapper
public interface DateTimeMapper {

    /*
     * =====================================
     * In the H2 Database
     * =====================================
     * SELECT current_timestamp, current_date, current_time ;
     */
    @Select("SELECT current_timestamp")
    LocalDateTime now();

} // end interface
