package com.example.BugTracking.Controller;

import com.example.BugTracking.Exception.ResourceNotFoundException;
import com.example.BugTracking.Model.Member;
import com.example.BugTracking.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController// for thyme leaf scanning. before, it was @RestController
@RequestMapping("/admin/")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public List<Member> getAllMembers(){
        return this.memberRepository.findAll();
    }

    //get member by id
    @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
    public ResponseEntity<Member> getMemberById(@PathVariable(value = "id") Long memberId) throws ResourceNotFoundException{
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->  new ResourceNotFoundException("The member with ID: "+ memberId + " was not" +
                "found in the system"));
        return ResponseEntity.ok().body(member);
    }

    // Save member
    @RequestMapping(value = "/member/save", method = RequestMethod.POST)
    public ResponseEntity<Member> saveMember(@RequestBody Member memberInfo){

        memberInfo.setUpdatedBy("Admin");
        memberInfo.setUpdatedOn(Instant.now().toEpochMilli());
        Member member = memberRepository.save(memberInfo);
        return ResponseEntity.ok().body(member);
    }

    //Update
    @RequestMapping(value = "/member/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable(value = "id") Long memberId,
                                               @Valid @RequestBody Member memberInfo) throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(
                "The member with ID: "+ memberId + " was not found in the system"));

        member.setAge(memberInfo.getAge());
        member.setDepartment(memberInfo.getDepartment());
        member.setDob(memberInfo.getDob());
        member.setFirstName(memberInfo.getFirstName());
        member.setMiddleName(memberInfo.getMiddleName());
        member.setLastName(memberInfo.getLastName());
        member.setUserName(memberInfo.getUserName());
        member.setUpdatedBy("Admin");
        member.setUpdatedOn(Instant.now().toEpochMilli());

        return ResponseEntity.ok().body(this.memberRepository.save(member));
    }

    //Delete
    @RequestMapping(value = "/member/delete/{id}")
    public boolean deleteMember(@PathVariable(value = "id") long memberId) throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId).
                orElseThrow(() -> new ResourceNotFoundException("The member with ID: "+ memberId + " was not found in the system"));
        if(member.getId() == memberId){
            memberRepository.deleteById(memberId);
            return true;
        }
        return false;
    }
}
