package com.xd.mapper;

import com.xd.pojo.Register;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface RegisterMapper {

    @Select("select * from user_enter_info where phone_num = #{phone_num} and user_type = #{user_type}")
    Register selectUserByPhoneNum(String phone_num, int user_type);

    @Select("select * from user_enter_info where user_id = #{user_id}")
    Register selectUserByUserId(Long user_id);



    @Insert("insert into user_enter_info"+
            "(phone_num, pass_word,  user_type, token, time, user_id)"+
            "values"+
            "(#{phone_num}, #{pass_word}, #{user_type}, #{token}, #{time}, #{user_id})")
    int insertUserInfo(Register register);

    @Update("update user_enter_info set user_id = #{id} where phone_num = #{phone_num}")
    Long updateUserIdByPhone(Long id, String phone_num);

    //Token
    @Select("select * from user_enter_info where phone_num = #{phone_num}")
    Register selectToken(String phone_num);
    @Update("update user_enter_info set token = #{token} where id = #{id}")
    int updateToken(String token, Long id);

    //修改手机号

}
