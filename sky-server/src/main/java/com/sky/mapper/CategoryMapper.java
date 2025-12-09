package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 修改
     * @param category
     */
    void update(Category category);

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(`name`,`type`,`sort`,`create_time`,`update_time`,`create_user`,`update_user`,`status`) values(#{name},#{type},#{sort},#{createTime},#{updateTime},#{createUser},#{updateUser},#{status})")
    void insert(Category category);

    /**
     *
     * @param type
     * @return
     */
    @Select("select * from category where type = IFNULL(#{type},type)")
    List<Category> GetByType(String type);
    @Delete("delete from  category where id = #{id}")
    void delect(String id);
}
