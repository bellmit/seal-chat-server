package com.mn.im.core.vo;

import com.mn.im.core.common.utils.PageQuery;
import com.mn.im.core.entity.MemberFriend;
import lombok.Data;

/**
 * @author qiaomengnan
 * @ClassName: MemberFriend
 * @Description: 好友关系
 * @date 2020-03-25
 */
@Data
public class MemberFriendVo extends PageQuery<MemberFriend> {

    /**
     * @Fields  : 用户ID
     * @author qiaomengnan
     */
    private String memberId;

    /**
     * @Fields  : 朋友ID
     * @author qiaomengnan
     */
    private String friendId;

    /**
     * @Fields  : 0. 正常好友 1. 已删除 2. 黑名单
     * @author qiaomengnan
     */
    private String status;


}
