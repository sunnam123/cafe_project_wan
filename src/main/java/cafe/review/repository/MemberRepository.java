package cafe.review.repository;

import cafe.review.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository implements MemberInterface {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return store.values().stream()
                .filter(m->m.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByNameEmail(String name, String email) {
        return store.values().stream()
                .filter(m->m.getName().equals(name)
                        &&m.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<Member> findByNamePhone(String name, String phone) {
        return store.values().stream()
                .filter(m->m.getName().equals(name)
                        &&m.getPhoneNumber().equals(phone))
                .findAny();
    }

    @Override
    public Optional<Member> findPasswordNameEmail(String loginId, String name, String email) {
        return store.values().stream()
                .filter(m->m.getLoginId().equals(loginId)
                        &&m.getName().equals(name)
                        &&m.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<Member> findPasswordNamePhone(String loginId, String name, String phone) {
        return store.values().stream()
                .filter(m->m.getLoginId().equals(loginId)
                        &&m.getName().equals(name)
                        &&m.getPhoneNumber().equals(phone))
                .findAny();
    }

    @Override
    public void update(Long loginId, MemberUpdateDto memberUpdateParam) {
        Member findMember = findById(loginId).orElseThrow();
        findMember.setPassword(memberUpdateParam.getPassword());
        findMember.setEmail(memberUpdateParam.getEmail());
        findMember.setPhoneNumber(memberUpdateParam.getPhoneNumber());
    }
}
