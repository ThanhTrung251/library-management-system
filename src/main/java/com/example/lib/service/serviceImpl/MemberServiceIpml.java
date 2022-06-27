package com.example.lib.service.serviceImpl;

import com.example.lib.config.exception.BadRequestException;
import com.example.lib.model.dto.DataMailDTO;
import com.example.lib.model.dto.MemberDTO;
import com.example.lib.model.entity.Member;
import com.example.lib.model.request.MemberRequest;
import com.example.lib.repository.MemberRepository;
import com.example.lib.service.MailService;
import com.example.lib.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberServiceIpml implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MailService mailService;

    @Override
    public List<MemberDTO> getAllMember() {
        return memberRepository.findAll().stream()
                .map(member -> mapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MemberDTO creatMember(MemberRequest memberRequest) {
        Member member = mapper.map(memberRequest, Member.class);
        memberRepository.save(member);

        DataMailDTO dataMail = new DataMailDTO();
        dataMail.setTo(memberRequest.getMail());
        dataMail.setSubject("Đăng kí thành công");

        Map<String, Object> props = new HashMap<>();
        props.put("name", memberRequest.getName());
        props.put("major", memberRequest.getMajor());
        props.put("mail", memberRequest.getMail());
        props.put("pwd", memberRequest.getPassword());
        props.put("date", memberRequest.getBirthday());
        props.put("expired", memberRequest.getExpired());
        dataMail.setProps(props);

        try {
            mailService.sendHtmlMail(dataMail, "mail/a-member");
            log.info("cập nhật thành viên có Id: " + member.getId());
        } catch (MessagingException exp) {
            log.error("gửi mail lỗi!", exp);
        }

        return mapper.map(member, MemberDTO.class);
    }

    @Override
    @Transactional
    public MemberDTO updateMember(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BadRequestException("Member not found"));

        member.setName(memberRequest.getName());
        member.setMajor(memberRequest.getMajor());
        member.setMail(memberRequest.getMail());
        member.setPassword(memberRequest.getPassword());
        member.setExpired(memberRequest.getExpired());
        member.setBirthday(memberRequest.getBirthday());

        memberRepository.save(member);

        DataMailDTO dataMail = new DataMailDTO();
        dataMail.setTo(memberRequest.getMail());
        dataMail.setSubject("Cập nhật thông tin thành công");

        Map<String, Object> props = new HashMap<>();
        props.put("name", memberRequest.getName());
        props.put("major", memberRequest.getMajor());
        props.put("mail", memberRequest.getMail());
        props.put("pwd", memberRequest.getPassword());
        props.put("date", memberRequest.getBirthday());
        props.put("expired", memberRequest.getExpired());
        dataMail.setProps(props);

        try {
            mailService.sendHtmlMail(dataMail, "mail/up-member");
            log.info("cập nhật thành viên có Id: " + member.getId());

        } catch (MessagingException exp) {
            log.error("gửi mail lỗi!", exp);
        }

        return mapper.map(member, MemberDTO.class);

    }

    @Override
    public void deleteMember(long id) {
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BadRequestException("Member not found"));
        log.info("Lấy thông tin memberId: " + id);
        MemberDTO dto = mapper.map(member, MemberDTO.class);
        return dto;

    }

    @Override
    public List<MemberDTO> searchMember(String keyword) {
        return memberRepository.searchMember(keyword).stream().map(member -> mapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }
}


