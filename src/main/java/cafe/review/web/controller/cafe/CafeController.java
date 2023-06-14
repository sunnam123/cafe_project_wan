package cafe.review.web.controller.cafe;


import cafe.review.domain.CafeMember;
import cafe.review.domain.Member;
import cafe.review.repository.cafeNameSearchCond;
import cafe.review.service.MemberServiceInterface;
import cafe.review.service.cafe.CafeMemberServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeMemberServiceInterface cafeMemberServiceInterface;
    private final MemberServiceInterface memberServiceInterface;


    @GetMapping("/All_list")
    public String All_list(Model model){
        List<CafeMember> cafeMembers = cafeMemberServiceInterface.findAll();
        model.addAttribute("cafes", cafeMembers);
        return "cafe/All_list";
    }
    @GetMapping("/{loginId}/All_list_login")
    public String All_list_login(@PathVariable String loginId, Model model){
        List<CafeMember> cafeMembers = cafeMemberServiceInterface.findAll();
        Member member = memberServiceInterface.findByLoginId(loginId).get();
        model.addAttribute("member",member);
        model.addAttribute("cafes", cafeMembers);
        return "cafe/All_list_login";
    }


    @GetMapping("/All_list/{cafeName}")
    public String detailsForm(@PathVariable String cafeName, Model model){
        CafeMember cafeMember = cafeMemberServiceInterface.findByCafeName(cafeName).get();
        model.addAttribute("cafes",cafeMember);
        return "cafe/details";
    }

    @GetMapping("/findByFran")
    public String findByFran(Model model){
        List<CafeMember> cafetypes = cafeMemberServiceInterface.findByFran();
        model.addAttribute("cafes", cafetypes);
        return "cafe/findByFran";
    }

    @GetMapping("/findByGam")
    public String findByGam(Model model){
        List<CafeMember> cafetypes2 = cafeMemberServiceInterface.findByGam();
        model.addAttribute("cafes", cafetypes2);
        return "cafe/findByGam";
    }
}

