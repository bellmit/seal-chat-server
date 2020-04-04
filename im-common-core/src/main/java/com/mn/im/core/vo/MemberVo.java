package com.mn.im.core.vo;

import com.mn.im.core.base.BaseEntity;
import com.mn.im.core.common.utils.PageQuery;
import com.mn.im.core.entity.Member;
import lombok.Data;

/**
 * @author qiaomengnan
 * @ClassName: Member
 * @Description: 用户信息
 * @date 2020-03-25
 */
@Data
public class MemberVo extends PageQuery<Member> {

    /**
     * @Fields  : 昵称
     * @author qiaomengnan
     */
    private String nickName;

    /**
     * @Fields  : 头像
     * @author qiaomengnan
     */
    private String headImgUrl;

    /**
     * @Fields  : 手机号,也是登录密码
     * @author qiaomengnan
     */
    private String mobile;

    /**
     * @Fields  : 登录密码
     * @author qiaomengnan
     */
    private String password;

    /**
     * @Fields  : 微信ID,微信注册使用
     * @author qiaomengnan
     */
    private String wxId;

    /**
     * @Fields  : IM号,可用于好友查找
     * @author qiaomengnan
     */
    private String imNo;

    /**
     * @Fields  : 会员序号
     * @author qiaomengnan
     */
    private Long memberNo;

}
