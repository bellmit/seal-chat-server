package com.mn.im.core.vo;

import com.mn.im.core.common.utils.PageQuery;
import com.mn.im.core.entity.MemberFriendAsk;
import lombok.Data;

/**
 * @author qiaomengnan
 * @ClassName: MemberFriendAsk
 * @Description: 好友请求
 * @date 2020-04-02
 */
@Data
public class MemberFriendAskVo extends PageQuery<MemberFriendAsk> {

    /**
     * @Fields  : 请求方
     * @author qiaomengnan
     */
    private String memberId;

    /**
     * @Fields  : 被请求方
     * @author qiaomengnan
     */
    private String askMemberId;

    /**
     * @Fields  : 状态 0.待通过 1.通过 2.拒绝 3.过期
     * @author qiaomengnan
     */
    private String status;

}
