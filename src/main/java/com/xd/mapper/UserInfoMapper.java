package com.xd.mapper;


import com.xd.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Mapper
@Repository
public interface UserInfoMapper {


    @Select("select * from user_enter_info where id = #{id}")
    Register selectPhoneNum(Long id);

    //Doctor















}
