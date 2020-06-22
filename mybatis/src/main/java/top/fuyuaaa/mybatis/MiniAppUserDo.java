package top.fuyuaaa.mybatis;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信小程序客户do 
 * @author : fuyuaaa
 * @date : 2020-01-06 15:55
 */
@Data
public class MiniAppUserDo implements Serializable {

    private static final long serialVersionUID = -4602040444736747867L;
    private Integer id;
    private String appId;
    private String unionid;
    private String openid;
    private String weChatName;
    private String sessionKey;
    private String phone;
    private Date lastEnterPersonalTime;
    private String weChatPicture;

    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
