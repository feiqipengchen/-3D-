package org.jeecg.modules.demo.customrecord.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.customrecord.entity.Customrecord;
import org.jeecg.modules.demo.customrecord.service.ICustomrecordService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-19
 * @Version: V1.0
 */
@Api(tags="定制衣服记录")
@RestController
@RequestMapping("/customrecord/customrecord")
@Slf4j
public class CustomrecordController extends JeecgController<Customrecord, ICustomrecordService> {
	@Autowired
	private ICustomrecordService customrecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param customrecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-分页列表查询")
	@ApiOperation(value="定制衣服记录-分页列表查询", notes="定制衣服记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Customrecord customrecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Customrecord> queryWrapper = QueryGenerator.initQueryWrapper(customrecord, req.getParameterMap());
		Page<Customrecord> page = new Page<Customrecord>(pageNo, pageSize);
		IPage<Customrecord> pageList = customrecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param customrecord
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-添加")
	@ApiOperation(value="定制衣服记录-添加", notes="定制衣服记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Customrecord customrecord) {
		customrecordService.save(customrecord);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param customrecord
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-编辑")
	@ApiOperation(value="定制衣服记录-编辑", notes="定制衣服记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Customrecord customrecord) {
		customrecordService.updateById(customrecord);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-通过id删除")
	@ApiOperation(value="定制衣服记录-通过id删除", notes="定制衣服记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		customrecordService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-批量删除")
	@ApiOperation(value="定制衣服记录-批量删除", notes="定制衣服记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.customrecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "定制衣服记录-通过id查询")
	@ApiOperation(value="定制衣服记录-通过id查询", notes="定制衣服记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Customrecord customrecord = customrecordService.getById(id);
		if(customrecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(customrecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param customrecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Customrecord customrecord) {
        return super.exportXls(request, customrecord, Customrecord.class, "定制衣服记录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Customrecord.class);
    }

}
