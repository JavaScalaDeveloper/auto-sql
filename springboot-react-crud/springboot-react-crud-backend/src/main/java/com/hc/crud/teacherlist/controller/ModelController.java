package com.hc.crud.teacherlist.controller;

import com.hc.crud.teacherlist.common.ApiResult;
import com.hc.crud.teacherlist.entity.Model;
import com.hc.crud.teacherlist.entity.ModelFields;
import com.hc.crud.teacherlist.repository.ModelFieldRepository;
import com.hc.crud.teacherlist.repository.ModelRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Resource
    private ModelRepository modelRepository;
    @Resource
    private ModelFieldRepository modelFieldRepository;

    @GetMapping("/getDataTypes/")
    public ApiResult<String> getDataTypes() {
        return ApiResult.ofData("成功");
    }

    @PostMapping("/save")
    @Transactional
    public ApiResult<Model> doSave(@RequestBody Model model) {
        Model saveRes = modelRepository.save(model);
        Integer id = saveRes.getId();
        List<ModelFields> fields = model.getFields();
        fields.forEach(l -> {
            l.setModelId(id);
            modelFieldRepository.save(l);
        });
        return ApiResult.ofData(saveRes);
    }

    @PostMapping("/edit")
    public ApiResult<Model> doEdit(@RequestBody Model model) {
        Model saveRes = modelRepository.save(model);
        return ApiResult.ofData(saveRes);
    }

    @GetMapping("/findAll")
    public ApiResult<List<Model>> doFindAll() {
        List<Model> all = modelRepository.findAll();
        return ApiResult.ofData(all);
    }

    @GetMapping("/findById/{id}")
    public ApiResult<Model> findById(@PathVariable("id") Integer id) {
        return ApiResult.ofData(modelRepository.findById(id).get());
    }

    @DeleteMapping("/deleteById/{id}")
    public ApiResult<String> deleteById(@PathVariable("id") Integer id) {
        modelRepository.deleteById(id);
        return ApiResult.ofData("成功");
    }

    @GetMapping("/getTbListByDsId/{id}")
    public ApiResult<List<String>> getTbListByDsId(@PathVariable("id") Long id) {
        //暂时不考虑多源，实际业务场景可根据数据源id获取数据库信息
        String dbName = "";
        List<String> res = modelRepository.getAllTablesByDsId(dbName);
        return ApiResult.ofData(res);
    }

    @GetMapping("/getFieldsByTbName/{name}")
    public ApiResult<List<ModelFields>> getFieldsByTbName(@PathVariable("name") String name) {
        List<List<Object>> res = modelRepository.getFieldsByTbName(name);
        List<ModelFields> collect = res.stream().map(l -> {
            ModelFields modelFields = new ModelFields();
            modelFields.setName(l.get(0).toString());
            modelFields.setAlias(l.get(1).toString());
            return modelFields;
        }).collect(Collectors.toList());
        return ApiResult.ofData(collect);
    }

}
