package com.leyou.item.mapper;

import com.leyou.item.pojo.SpecParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SpecParamMapper extends Mapper<SpecParam> {

    @Delete("DELETE FROM tb_spec_param WHERE group_id = #{gid}")
    void deleteByGroupId(@Param("gid") Long gid);
}
