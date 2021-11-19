package com.atguigu.serviceedu.controller;


import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        //调用service方法查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }
}

