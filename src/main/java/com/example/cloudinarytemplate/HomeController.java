package com.example.cloudinarytemplate;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CloudinaryConfig cloudc;


    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());   //findall returns a collection, can be converted to arraylist
        return "list";
    }

    @GetMapping("/add")
    public String newCar(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/add")
    public String processActor(@ModelAttribute Car car, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            car.setImage("https://res.cloudinary.com/nk4363/image/upload/v1628530719/car-outline-icon-vector_acdxwf.webp");
            carRepository.save(car);
            return "redirect:/";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            String tempurl = uploadResult.get("url").toString();
//            tempurl.indexOf("upload/");
//                    "/w_1000,ar_1:1,c_fill,g_auto/"
            car.setImage(uploadResult.get("url").toString());
            carRepository.save(car);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }


}