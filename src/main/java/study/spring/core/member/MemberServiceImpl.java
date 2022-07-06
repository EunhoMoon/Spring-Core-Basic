package study.spring.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Autowired
	// Autowired : ComponentScan으로 등록된 Bean 중 해당 타입에 맞는 의존객체를 찾아 자동으로 주입
	// 기본 조회 전략은 type이 같은 Bean을 주입한다.
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
