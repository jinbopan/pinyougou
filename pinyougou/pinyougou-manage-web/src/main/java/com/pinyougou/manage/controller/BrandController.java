package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.Result;
import com.pinyougou.vo.PageResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference(timeout = 50000)
    private BrandService brandService;

    @GetMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.queryAll();
    }

    @GetMapping("/findPage")
    public PageResult findAll(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        //分页查询
        return brandService.findPage(pageNo, rows);
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("添加失败");
        }
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return Result.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("修改失败");
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return TbBrand
     */
    @GetMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findById(id);
    }

    /**
     * 根据id集合删除品牌列表
     *
     * @param ids
     * @return Result
     */
    @GetMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            //brandService.delete(ids);
            brandService.batchDelete(ids);
            return Result.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,
                             @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        return brandService.search(brand, pageNo, rows);
    }

    /**
     * 查询品牌列表
     *
     * @return
     */
    @PostMapping("/selectOptionList")
    public List<Map<String, String>> selectOptionList() {
        return brandService.selectOptionList();
    }
}
