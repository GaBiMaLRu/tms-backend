package com.trans.tms.controller;


import com.trans.tms.DTO.UserProfileDTO;
import com.trans.tms.common.context.RequestContext;
import com.trans.tms.common.utils.Result;
import com.trans.tms.entity.Member;
import com.trans.tms.feign.UserClient;
import com.trans.tms.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 运单表 前端控制器
 * </p>
 *
 * @author diesel
 * @since 2020-03-19
 */
@Slf4j
@Api(tags = "用户管理")
@Controller
@RequestMapping("user")
public class UserController {

    private final UserClient userClient;

    private final IMemberService memberService;

    public UserController(UserClient userClient, IMemberService memberService) {
        this.userClient = userClient;
        this.memberService = memberService;
    }

    @SneakyThrows
    @ApiOperation(value = "我的信息")
    @ResponseBody
    @GetMapping("profile")
    public Result profile() {

        //    并放入参数
        String userId = RequestContext.getUserId();

        Member member = memberService.detail(userId);
        if (member != null) {
            return Result.ok().put("data", UserProfileDTO.builder()
                    .id(userId)
                    .avatar(member.getAvatar())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .build());
        } else {
            return Result.ok().put("data", UserProfileDTO.builder().build());
        }
    }
}
