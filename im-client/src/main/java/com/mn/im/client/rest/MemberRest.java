package com.mn.im.client.rest;

import com.mn.im.client.service.MemberService;
import com.mn.im.core.common.extend.response.RestResponse;
import com.mn.im.core.common.extend.response.RestResponseGenerator;
import com.mn.im.core.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaomengnan
 * @ClassName: MemberRest
 * @Description:
 * @date 2020-04-03
 */
@RestController
@RequestMapping("/member")
public class MemberRest {

    @Autowired
    private MemberService memberService;

    /**
     * @Title:
     * @Description:   注册
     * @param memberVo
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/04/03 10:28:37
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> register(@RequestBody MemberVo memberVo){
        this.memberService.register(memberVo);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(), HttpStatus.OK);
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
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> login(@RequestBody MemberVo memberVo){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(
            this.memberService.login(memberVo)
        ), HttpStatus.OK);
    }

}
