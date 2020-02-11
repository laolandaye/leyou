package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.groupMapper.select(specGroup);
    }

    /**
     * 新增 规格参数组
     * @param group
     */
    @Transactional
    public void saveGroup(SpecGroup group) {
        this.groupMapper.insertSelective(group);
    }

    /**
     *  修改 规格参数组
     * @param group
     */
    @Transactional
    public void updateGroup(SpecGroup group) {
        this.groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     *  删除 规格参数组
     * @param group
     */
    @Transactional
    public void deleteGroup(SpecGroup group) {
        this.groupMapper.deleteByPrimaryKey(group);
        this.paramMapper.deleteByGroupId(group.getId());
    }

    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.queryParams(g.getId(), null, null, null));
        });
        return groups;
    }

        /**
         * 根据gid查询规格参数
         * @param gid
         * @return
         */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.paramMapper.select(record);
    }

    @Transactional
    public void saveParam(SpecParam param) {
        this.paramMapper.insertSelective(param);
    }

    @Transactional
    public void updateParam(SpecParam param) {
        this.paramMapper.updateByPrimaryKeySelective(param);
    }

    @Transactional
    public void deleteParam(SpecParam param) {
        this.paramMapper.deleteByPrimaryKey(param);
    }
}
