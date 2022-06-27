package com.example.lib.service;

import com.example.lib.model.dto.MemberDTO;
import com.example.lib.model.request.MemberRequest;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMember();
    MemberDTO creatMember(MemberRequest memberRequest) ;
    MemberDTO updateMember(Long id,MemberRequest memberRequest);
    void deleteMember(long id);
    MemberDTO getMemberById(Long id);

    List<MemberDTO> searchMember(String keyword);
}
