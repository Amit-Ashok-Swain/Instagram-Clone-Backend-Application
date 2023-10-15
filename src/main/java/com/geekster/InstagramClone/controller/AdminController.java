package com.geekster.InstagramClone.controller;


import com.geekster.InstagramClone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @PutMapping("user/{id}/{blueTick}")
    String toggleBlueTick(@PathVariable Long id,@PathVariable boolean blueTick)
    {
        return adminService.toggleBlueTick(id,blueTick);
    }

}
