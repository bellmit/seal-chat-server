package com.mn.im.client.service;

import com.mn.im.core.base.BaseService;
import com.mn.im.core.common.constant.common.RedisKeys;
import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.utils.StringUtils;
import com.mn.im.core.common.utils.UUIDUtils;
import com.mn.im.core.entity.Member;
import com.mn.im.core.repo.MemberRepo;
import com.mn.im.core.vo.MemberVo;
import org.springframework.stereotype.Service;

/**
 * @author qiaomengnan
 * @ClassName: MemberService
 * @Description:
 * @date 2020-04-02
 */
@Service
public class MemberService extends BaseService<MemberRepo, Member, MemberVo> {

    /**
     * @Fields : token超时时间
     * @author qiaomengnan
     */
    public static final int EXPIRE_TIME = 30 * 60;

    /**
     * @Title:
     * @Description:   注册
     * @param memberVo
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/04/03 10:28:37
     */
    public void register(MemberVo memberVo) {
        Member member = new Member();
        member.setId(UUIDUtils.getUUID());
        member.setMobile(memberVo.getMobile());
        member.setPassword(memberVo.getPassword());
        this.save(member);
    }

    /**
     * @Title:
     * @Description:   登录
     * @param memberVo
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/04/03 10:28:42
     */
    public String login(MemberVo memberVo) {
        MemberVo res = this.findVoByMobile(memberVo.getMobile());
        if(res == null || StringUtils.notEquals(res.getPassword(),memberVo.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        return getToken(memberVo.getMobile());
    }

    /**
     * @Title:
     * @Description:   根据手机号查询用户
     * @param mobile
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/04/03 10:50:17
     */
    public MemberVo findVoByMobile(String mobile) {
        MemberVo params = new MemberVo();
        params.setMobile(mobile);
        return this.findFirstVo(params);
    }

    /**
     * @param userName
     * @return
     * @throws
     * @Title:
     * @Description: 返回token
     * @author qiaomengnan
     * @date 2019/02/27 02:50:43
     */
    public String getToken(String userName) {
        if (StringUtils.isTrimBlank(userName))
            throw new ServiceException("未获取到用户名");
        String result;
        Object res = this.redisService.get(StringUtils.join(RedisKeys.USER_TOKEN_NAME_PREFIX, userName));
        if (StringUtils.isTrimBlank(res)) {
            // 使用UUID作为token
            result = UUIDUtils.getUUID();
            redisService.save(StringUtils.join(RedisKeys.USER_TOKEN_NAME_PREFIX, userName), result, EXPIRE_TIME);
            redisService.save(StringUtils.join(RedisKeys.USER_TOKEN_PREFIX, result), userName, EXPIRE_TIME);
        } else {
            result = res.toString();
            redisService.expire(StringUtils.join(RedisKeys.USER_TOKEN_NAME_PREFIX, userName), EXPIRE_TIME);
            redisService.expire(StringUtils.join(RedisKeys.USER_TOKEN_PREFIX, result), EXPIRE_TIME);
        }
        return result;
    }

}
