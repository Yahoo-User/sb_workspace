package org.zerock.myapp.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.myapp.domain.Hotel;


@Mapper
public interface HotelMapper {

    // sample - Using Mapper XML file.
    Hotel selectByCityId(Integer cityId);

} // end interface
