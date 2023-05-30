package hospital.hospitalserivce.service;

import hospital.hospitalserivce.domain.Member;
import hospital.hospitalserivce.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    //    @Autowired private MemberRepository memberRepository; //필드 주입 xxx

    private final MemberRepository memberRepository;
//    public MemberService (MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @RequiredArgsConstructor이 final을 가지고 있는 변수로 생성자를 만들어줌


    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }
}
