package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Ftype;
import com.server.FtypeServer;
import com.util.PageBean;

import net.sf.json.JSONObject;

@Controller
public class FtypeController {
	@Resource
	private FtypeServer ftypeService;
	
	//查询所有科室信息
	@RequestMapping("ftype.do")
	public String ftype(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map) {
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		//Sysuser u=(Sysuser)session.getAttribute("user");
		int total=ftypeService.getCount(null);
		pageBean.setTotal(total);
		List<Ftype> list=ftypeService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "typeList";
	}
	
	//查询所有科室信息--搜索
	@RequestMapping("searchFtype.do")
	public String searchFtype(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Ftype ftype) {
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> amap=new HashMap<>();
		pmap.put("name", ftype.getName());
		amap.put("name", ftype.getName());
		//System.out.println(amap);
		int total=ftypeService.getCount(amap);
		pageBean.setTotal(total);
		List<Ftype> list = ftypeService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		//map.put("flist", ftypeService.getAll(null));
		return "typeList";
	}
	
	//新增科室
	@RequestMapping("admin/addType.do")
	public void addFtype(Ftype Ftype,HttpServletResponse response){
		   Map<String, Object> map=new HashMap<String, Object>();
		   map.put("name", Ftype.getName());
		   System.out.println("uname==="+ftypeService.checkUname(map));
		   JSONObject obj=new JSONObject();
		   if(ftypeService.checkUname(map)!=null){
				 obj.put("info", "ok");
			   }else{
				   Ftype Ftype1=new Ftype();
				   Ftype1.setName(Ftype.getName());
				   ftypeService.add(Ftype1);
				   obj.put("info", "可以用！");
				  
			   }
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter out=null;
		   try {
			out=response.getWriter();
			out.print(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		   
	}
	
	//修改科室
	@RequestMapping("admin/doUpdateFtype.do")
	public String doUpdateFtype(ModelMap map,int id){
		map.put("ftype", ftypeService.getById(id));
		return "admin/ftype_update";
	}
	// 保存修改
		@RequestMapping("admin/updateFtype.do")
		public void updateFtype(Ftype ftype, HttpServletResponse response) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", ftype.getName());
			System.out.println("uname===" + ftypeService.checkUname(map));
			JSONObject obj = new JSONObject();
			if (ftypeService.checkUname(map) != null && !ftypeService.checkUname(map).getName().equals(ftype.getName())) {
				obj.put("info", "ok");
			} else {
				Ftype ftype1 = ftypeService.getById(ftype.getId());
				ftype1.setName(ftype.getName());
				ftypeService.update(ftype1);
				obj.put("info", "可以用！");

			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print(obj);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}

		}

//	分页查询
	@RequestMapping("admin/TypeList.do")
	public String goodList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		//Sysuser u=(Sysuser)session.getAttribute("user");
		int total=ftypeService.getCount(null);
		pageBean.setTotal(total);
		List<Ftype> list=ftypeService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "admin/list_type";
	}
//   分页模糊查询
	@RequestMapping("admin/vagueFtypeList.do")
	public String vagueFtypeList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		//Sysuser u=(Sysuser)session.getAttribute("user");
		Map<String, Object> amap=new HashMap<>();
		/*amap.put("uid", u.getId());
		pmap.put("uid", u.getId());*/
		int total=ftypeService.getCount(amap);
		pageBean.setTotal(total);
		List<Ftype> list=ftypeService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		return "admin/list_Ftype";
	}
	
	@RequestMapping("admin/deleteFtype.do")
	public String deleteFtype(int id){
		ftypeService.delete(id);
		return "redirect:TypeList.do";
	}
}
