package com.trans.tms.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.entity.Member;
import com.trans.tms.mapper.MemberMapper;
import com.trans.tms.service.IMemberService;
import org.springframework.stereotype.Service;

/**
 * 用户服务类实现
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}