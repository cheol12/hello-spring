package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    // 테스트 실행시 순서 의존성을 없애기 위해 실행한 데이터들을 깔끔히 지워주는 함수를 만들고 가져온다.


    @Test
    void 회원가입() {
        // given 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("spring");    // hello가 주어졌고

        // when 이걸 실행했을 때
        Long saveId = memberService.join(member);   // 멤버 정보를 꺼냈을 때

        // then 결과가 이것으로 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);    // join 절차 = 중복검사 -> 가입
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");




//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
//        }

        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}