package com.example.jecktao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jecktao.domain.baseDo.BaseRq;
import com.example.jecktao.entity.Tools;
import com.example.jecktao.service.IToolsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 工具表 前端控制器
 * </p>
 *
 * @author leitao
 * @since 2021-01-16
 */
@RestController
@RequestMapping("/jecktao/tools")
public class ToolsController{
    @Autowired
    private IToolsService toolsService;

    @ApiOperation("查询所有的工具")
    @GetMapping("/queryAll")
    public BaseRq<List<Tools>> queryAll() {
        BaseRq<List<Tools>> listBaseRq = new BaseRq<>();
        List<Tools> list = toolsService.list();
        listBaseRq.setData(list);
        return listBaseRq;
    }

    @ApiOperation("精确查询单个工具的信息")
    @PostMapping("/queryOneByName")
    public BaseRq<Tools> queryOneByName(@RequestBody @ApiParam(name="软件全名") String toolName) {
        BaseRq<Tools> toolsBaseRq = new BaseRq<>();
        QueryWrapper<Tools> queryWrapper = new QueryWrapper<Tools>();
        queryWrapper.eq("name", toolName);
        Tools result = toolsService.getOne(queryWrapper, true);
        toolsBaseRq.setData(result);
        return toolsBaseRq;
    }

    @ApiOperation("模糊查询工具名称")
    @PostMapping("/queryListByName")
    public BaseRq<List<Tools>> queryListByName(@RequestBody @ApiParam("部分软件名字") String toolName) {
        BaseRq<List<Tools>> listBaseRq = new BaseRq<>();
        if (toolName.equals(" ")) {
            List<Tools> list = toolsService.list();
            listBaseRq.setData(list);
        } else {
            QueryWrapper<Tools> wrapper = new QueryWrapper<>();
            wrapper.like("name", toolName);
            List<Tools> listRes = toolsService.list(wrapper);
            listBaseRq.setData(listRes);
        }

        return listBaseRq;
    }
}
