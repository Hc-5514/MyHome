package home.user.controller;

import home.user.model.UserDto;
import home.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session) throws Exception {
        try {
            ResponseEntity<UserDto> entity = new ResponseEntity<UserDto>(userService.userLogin(userDto), HttpStatus.OK);

            if (entity.getBody() != null) session.setAttribute("logOK", entity.getBody());

            return entity;
        } catch (Exception e) {
            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> signup(UserDto userDto) throws Exception {
        try {
            return new ResponseEntity<Integer>(userService.userEnroll(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("logOK");
        return "index";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "user/userInfo";
    }

    @PostMapping("/modify")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> modify(@RequestBody UserDto userDto, HttpSession session) throws Exception {
        try {
            userService.userModify(userDto);
            session.setAttribute("logOK", userDto);
            return new ResponseEntity<Integer>(1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id, HttpSession session) throws Exception {
        try {
            session.invalidate();
            return new ResponseEntity<Integer>(userService.userDelete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
