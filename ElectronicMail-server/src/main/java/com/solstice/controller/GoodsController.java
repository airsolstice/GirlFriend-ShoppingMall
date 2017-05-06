package com.solstice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.solstice.bean.Goods;
import com.solstice.bean.GoodsCatalog;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.exception.UserException;
import com.solstice.service.GoodsService;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GoodsController.class);

	@Autowired
	private GoodsService goodService;

	private static final int PAGAE_LEN = 20;
	private static final int All_PAGAE_LEN = 100;

	/**
	 * 获取分页页数
	 * 
	 * @param request
	 * @param catalog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/catalog/size", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String getCatalogPageSize(HttpServletRequest request, int catalog) {
		Result result = null;
		try {
			int sum = goodService.getCatalogGoodsSum(catalog);
			if (sum <= 0) {
				result = new Result(ResultCode.FAIL, "暂无"
						+ GoodsCatalog.valueOf(catalog) + "类商品", 0);
			} else {
				int pageSzie = sum / PAGAE_LEN + 1;
				result = new Result(ResultCode.SUCESS, "获取商品分页数", pageSzie);
			}
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}
	/**
	 * 获取所有商品
	 *
	 * @param index
	 * @param request
	 * @param catalog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/catalog/getAllGoods", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String getAllGoods(HttpServletRequest request, int index) {
		Result result = null;
		try {
			// 前端需要输入的index必须大于0
			int rows=16;
			int start = (index - 1) * rows;
			int length = rows;
			List<Goods> list = goodService.getAllGoods(start, length
					);

			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "暂无类商品", list);
			} else {
				result = new Result(ResultCode.SUCESS, String.format("获取第%s页商品", index),
						list);
			}
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 获取单个类别的商品(分页)
	 *
	 * @param index
	 * @param request
	 * @param catalog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/catalog/page", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String getCatalogGoodsPage(HttpServletRequest request, int index,
			int catalog) {
		Result result = null;
		try {
			// 前端需要输入的index必须大于0
			int start = (index - 1) * PAGAE_LEN;
			int length = PAGAE_LEN;
			List<Goods> list = goodService.getCatalogGoodsPage(start, length,
					catalog);
            Long  totalCounts=goodService.getCatalogGoodsTotalRows(catalog);
            Long  totalpage=null;
			if(totalCounts!=null){
				totalpage= (totalCounts  +  PAGAE_LEN - 1)/PAGAE_LEN ;  
			}else{
				totalpage=0L;
			}
            
			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "暂无"
						+ GoodsCatalog.valueOf(catalog) + "类商品", list,totalpage);
			} else {
				result = new Result(ResultCode.SUCESS, String.format("获取"
						+ GoodsCatalog.valueOf(catalog) + "类第%s页商品", index),
						list,totalpage);
			}
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 获取指定类型商品的前十条数据
	 * 
	 * @param request
	 * @param catalog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/catalog/top10", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String getCatalogGoodsTop10(HttpServletRequest request, int catalog) {
		Result result = null;
		try {
			List<Goods> list = goodService.getCatalogGoodsTop10(catalog);

			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "暂无"
						+ GoodsCatalog.valueOf(catalog) + "类商品", list);
			} else {
				result = new Result(ResultCode.SUCESS, "获取"
						+ GoodsCatalog.valueOf(catalog) + "类商品TOP 10", list);
			}
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 商品搜索
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/search", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String findGoodsByKey(HttpServletRequest request, String key) {
		Result result = null;
		try {
			key = new String(key.getBytes("8859_1"), "utf8");
			
			// 前端需要限定输入的关键字不能为空
			List<Goods> list = goodService.findGoodsByKey(key);

			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "找不到与\"" + key
						+ "\"相关的商品", list);
			} else {
				result = new Result(ResultCode.SUCESS, "获取与\"" + key
						+ "\"相关的商品", list);
			}

		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 添加商品
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String addGoods(HttpServletRequest request, Goods goods) {
		Result result = null;
		System.out.println("\n\n\n\n" + goods);
		try {
			goodService.addGoods(goods);
			result = new Result(ResultCode.SUCESS, "添加成功", true);
		}  catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 删除商品
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String deleteGoods(HttpServletRequest request, int id) {
		Result result = null;
		try {
			goodService.deleteGoods(id);
			result = new Result(ResultCode.SUCESS, "删除成功", true);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 更新name字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update/name", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updateName(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateName(goods);
			result = new Result(ResultCode.SUCESS, "商品名称更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 更新detail字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update/detail", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updateDetail(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateDetail(goods);
			result = new Result(ResultCode.SUCESS, "商品详情更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 更新price字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update/price", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updatePrice(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updatePrice(goods);
			result = new Result(ResultCode.SUCESS, "商品价格更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 更新catalog字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update/catalog", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updateCatalog(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateCatalog(goods);
			result = new Result(ResultCode.SUCESS, "商品分类更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	/**
	 * 更新url字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update/url", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updateUrl(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateUrl(goods);
			result = new Result(ResultCode.SUCESS, "商品链接更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

	@Test
	public void testEnumGoods() {
		int i = GoodsCatalog.HOUSEHOLD.value();
		GoodsCatalog str = GoodsCatalog.valueOf(i);
		System.out.println(str);
	}

	
	
	/**
	 * 更新url字段
	 * 
	 * @param request
	 * @param goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateGoods", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String updateGoods(HttpServletRequest request, Goods goods) {
		Result result = null;
		try {
			goodService.updateGoods(goods);
			result = new Result(ResultCode.SUCESS, "商品更新成功", true);
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
			return result.toString();
		}
		return result.toString();
	}

}
