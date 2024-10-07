package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.mybatis.mapper.CityMapper;
import org.zerock.myapp.mybatis.mapper.DateTimeMapper;
import org.zerock.myapp.mybatis.mapper.HotelMapper;

import java.util.Arrays;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Component  // * Required *
public class MyCommandLineRunner implements CommandLineRunner {
    @Setter(onMethod_ = @Autowired)
    private CityMapper cityMapper;

    @Setter(onMethod_ = @Autowired)
    private DateTimeMapper dateTimeMapper;

    @Setter(onMethod_ = @Autowired)
    private HotelMapper hotelMapper;


    @Override
    public void run(String... args) {
        log.trace("run({}) invoked.", Arrays.toString(args));

        Objects.requireNonNull(this.cityMapper);
        log.info("\t+1. this.cityMapper: {}, type: {}", this.cityMapper, this.cityMapper.getClass().getName());

        Objects.requireNonNull(this.dateTimeMapper);
        log.info("\t+2. this.dateTimeMapper: {}, type: {}", this.dateTimeMapper, this.dateTimeMapper.getClass().getName());

        Objects.requireNonNull(this.hotelMapper);
        log.info("\t+3. this.hotelMapper: {}, type: {}", this.hotelMapper, this.hotelMapper.getClass().getName());
    } // run

} // end class
