package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Category;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "菜品相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO){

        log.info("修改分类");
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){

        log.info("分类分页查询{}",categoryPageQueryDTO);
        PageResult pageResult =categoryService.pageQuery(categoryPageQueryDTO);

        return Result.success(pageResult);
    }


    /**
     * 禁用启用
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("禁用启用分类")
    public Result startOrStop(@PathVariable Integer status , Long id){
        log.info("启用禁用员工账号: {},{}",status,id);
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增员工: {}",categoryDTO);
        categoryService.save(categoryDTO);
        return  Result.success();
    }

    /**
     * 根据类型查分类
     * @param type
     * @return
     */
    @GetMapping("list")
    @ApiOperation("根据类型查分类")
    public Result<List<Category>> GetByType(String type){

        log.info("查询类型{}",type);
        List<Category> categories = categoryService.GetByType(type);

        return Result.success(categories);
    }

    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result delect(String id){
        log.info("删除id：{}",id);
        categoryService.delect(id);
        return Result.success();
    }
}
