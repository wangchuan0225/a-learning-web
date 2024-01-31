package com.example.myweb1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myweb1.entity.Selectresult;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoogleMapper extends BaseMapper<Selectresult> {
    @Select("select * from google")
    List<Selectresult> selectFromGoogle();
}
