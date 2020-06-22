package top.fuyuaaa.mybatis;

import org.apache.ibatis.annotations.Select;

/**
 * @author : fuyuaaa
 * @date : 2020-06-18 16:27
 */
public interface MiniAppUserDao {
    @Select("SELECT * FROM mini_app_user WHERE openid = #{openid}")
    MiniAppUserDo findByOpenid(String openid);
}
