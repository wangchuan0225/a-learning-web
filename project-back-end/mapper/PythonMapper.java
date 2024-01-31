package com.example.myweb1.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myweb1.entity.Selectresult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PythonMapper extends BaseMapper<Selectresult> {
    @Select("select * from python where id=#{id}")
    Selectresult select(String id);
    @Delete("delete from python where 1")
    void deletepython();

}
