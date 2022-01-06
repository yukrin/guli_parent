package com.atguigu.serviceedu.controller;


import com.atguigu.common.utils.R;
import com.atguigu.servicebase.exceptionHandler.GuliException;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.query.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        //调用service方法查询所有的操作
        try {
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(20001,"执行了自定义异常处理...");
        }

        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable Long id){
         teacherService.removeById(id);
         return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageList(@PathVariable long current,
                      @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        teacherService.page(pageTeacher,null);
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        long total = pageTeacher.getTotal();//总记录数

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacherCondition/{current}/{limit}")
    public R pageQuery(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable Long current,
                       @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                       @ApiParam(name = "teacherQuery", value = "查询对象", required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> pageParam = new Page<>(current, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R save(@ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable Long id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable Long id,
                        @ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher teacher){
        teacher.setId(id);
        Boolean flag = teacherService.updateById(teacher);
        if(flag){
            return R.ok();
        }
        return R.error();
    }
}

