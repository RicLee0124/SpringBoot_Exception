package com.lrm.web;

import com.lrm.domain.User;
import com.lrm.domain.UserRepository;
import com.lrm.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password,
                            HttpSession session){
        User user = userRepository.findUserByUsernameAndPassword(username,password);
        if(user!=null){
            session.setAttribute("user",user);
            return "index";
        }
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @PostMapping("/register")
    public String  register(@Valid UserForm userForm, BindingResult result){
//        boolean boo = true;
        if(!userForm.confirmPassword()){
            result.rejectValue("confirmPassword","confirmError","两次密码不一致");
//            boo = false;
        }
        if(result.hasErrors()){
            return "register"; // 如果使用@Valid注解，就表示已经将userForm和result放到了model中，直接可以在前端获取信息
        }
//        if(result.hasErrors()){
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            for(FieldError error : fieldErrors){
//                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
//            }
//            boo = false;
//        }
//        if(!boo){
//            return "register";
//        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/exception")
    public String testException(){
        throw new RuntimeException("异常处理测试");
    }

}
