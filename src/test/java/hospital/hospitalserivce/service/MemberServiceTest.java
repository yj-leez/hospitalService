package hospital.hospitalserivce.service;

import hospital.hospitalserivce.domain.Member;
import hospital.hospitalserivce.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("Jo");
        member.setAge(22);
        member.setGender("F");

        Long saveId = memberService.join(member);

        Assert.assertEquals(member, memberRepository.findOne(saveId));

    }


}