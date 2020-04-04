package com.mn.im.core.repo;

import com.mn.im.core.base.AbstractBaseRepository;
import com.mn.im.core.dao.MemberDao;
import com.mn.im.core.entity.Member;
import org.springframework.stereotype.Repository;

/**
 * @author qiaomengnan
 * @ClassName: MemberRepo
 * @Description:
 * @date 2020-04-02
 */
@Repository
public class MemberRepo extends AbstractBaseRepository<MemberDao, Member> {



}
