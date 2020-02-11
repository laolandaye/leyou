package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类id查询分组
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * 新增 规格参数组
     * @param group
     */
    @PostMapping("group")
    public ResponseEntity<Void> saveGroup(@RequestBody SpecGroup group){
        this.specificationService.saveGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改 规格参数组
     * @param group
     */
    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(@RequestBody SpecGroup group){
        this.specificationService.updateGroup(group);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除 规格参数组
     * @param id
     */
    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id){
        SpecGroup group = new SpecGroup();
        group.setId(id);
        this.specificationService.deleteGroup(group);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> list = this.specificationService.querySpecsByCid(cid);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid", required = false)Long gid,
            @RequestParam(value = "cid", required = false)Long cid,
            @RequestParam(value = "generic", required = false)Boolean generic,
            @RequestParam(value = "searching", required = false)Boolean searching
    ){

        List<SpecParam> params = this.specificationService.queryParams(gid, cid, generic, searching);

        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    /**
     * 新增 规格参数
     * @param param
     */
    @PostMapping("param")
    public ResponseEntity<Void> saveParam(@RequestBody SpecParam param){
        this.specificationService.saveParam(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改 规格参数组
     * @param param
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(@RequestBody SpecParam param){
        this.specificationService.updateParam(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除 规格参数组
     * @param id
     */
    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable("id") Long id){
        SpecParam param = new SpecParam();
        param.setId(id);
        this.specificationService.deleteParam(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
