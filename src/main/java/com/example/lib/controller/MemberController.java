package com.example.lib.controller;

import com.example.lib.model.dto.MemberDTO;
import com.example.lib.model.request.MemberRequest;
import com.example.lib.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private ModelMapper mapper;

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = {"/members"})
    public String MemberList(Model model, @RequestParam(defaultValue = "") String keyword) {
        List<MemberDTO> memberDTOList = memberService.searchMember(keyword);
        model.addAttribute("memberDTOList", memberDTOList);
        return "member/members";
    }

    @GetMapping("member")
    public String add(Model model) {
        model.addAttribute("member", new MemberRequest());
        return "member/add-member";
    }

    @PostMapping("member")
    public ResponseEntity<?> doAdd(@RequestBody @Valid MemberRequest memberRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberService.creatMember(memberRequest));
    }

    @GetMapping("member/update")
    public String addUpdate(Model model, @RequestParam Long id){
        MemberDTO member = memberService.getMemberById(id);
        MemberRequest memberRequest = mapper.map(member, MemberRequest.class);
        model.addAttribute("memberId", id);
        model.addAttribute("member", memberRequest);

        return "member/update-member";
    }

    @PostMapping("member/update")
    public ResponseEntity<?> doUpdate(@RequestParam Long id, @RequestBody @Valid MemberRequest memberRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(id, memberRequest));
    }


}
