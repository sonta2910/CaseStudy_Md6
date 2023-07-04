package com.casestudymodule6.controller;

import com.casestudymodule6.model.dto.ChangePasswordDTO;
import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.service.account.IAccountService;
import com.casestudymodule6.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("puzzling/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @PutMapping("/{account}")
    @PreAuthorize("@authorizationEvaluator.canUpdateThisUser(#account)")
    public ResponseEntity<User> updateUser(@PathVariable Account account, @RequestBody User user) {
        user.setId(account.getUser().getId());
        account.setUser(user);
        return new ResponseEntity<>(accountService.save(account).getUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> viewUserDetail(@PathVariable("id") Long id) {
        Optional<Account> optionalAccount=accountService.findById(id);
        return optionalAccount.map(account -> new ResponseEntity<>(account.getUser(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/changePassword/{account}")
    @PreAuthorize("@authorizationEvaluator.canUpdateThisUser(#account)")
    public ResponseEntity<Void> changePassword(@PathVariable Account account, @RequestBody ChangePasswordDTO changePasswordDTO)
    {
        if (account.getPassword().equals(changePasswordDTO.getOldPassword()))
        {
            if (changePasswordDTO.getConfirmPassword().equals(changePasswordDTO.getNewPassword()))
            {
                account.setPassword(changePasswordDTO.getNewPassword());
                accountService.save(account);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @GetMapping("/check/{account}")
    public ResponseEntity<String> checkPassword(@RequestParam String password, @PathVariable Account account)
    {
        if(Objects.equals(account.getPassword(), password))
        {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.ok("NO");
    }
    @GetMapping("/checkEmail/{account}")
    public ResponseEntity<String> checkEmail(@RequestParam String email,@PathVariable Account account){
        if(email.equals(account.getUser().getEmail()))
            return ResponseEntity.ok("OK");
        Optional<User> optionalUser=userService.findUserByEmail(email);
        if(optionalUser.isEmpty())
            return ResponseEntity.ok("OK");
        return ResponseEntity.ok("NO");
    }
    @GetMapping("/checkPhone/{account}")
    public ResponseEntity<String> checkPhone(@RequestParam String phone,@PathVariable Account account){
        if(phone.equals(account.getUser().getPhone()))
            return ResponseEntity.ok("OK");
        Optional<User> optionalUser=userService.findUserByPhone(phone);
        if(optionalUser.isEmpty())
            return ResponseEntity.ok("OK");
        return ResponseEntity.ok("NO");
    }
}
