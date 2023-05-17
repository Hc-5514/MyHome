package home.apt.controller;

import home.apt.model.AptDto;
import home.apt.model.service.AptService;
import home.dong.model.DongDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/house")
public class AptController {

    @Autowired
    private AptService service;

    @GetMapping("/search")
    public String aptSearch() throws Exception {
        return "content/search";
    }

    @PostMapping("/search")
    @ResponseBody
    public ResponseEntity<?> aptSearch(@RequestBody DongDto dongDto, Model model) throws Exception {
        try {
            return new ResponseEntity<List<AptDto>>(service.getAptList(dongDto.getDongName()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
