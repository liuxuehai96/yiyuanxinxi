package com.controller;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Sysuser;
import com.entity.BbsWithBLOBs;
import com.server.BbsServer;
import com.server.PicServer;
import com.server.SysuserServer;
import com.util.PageBean;

@Controller
public class BbsController {
	@Resource
	private BbsServer bbsService;
	@Resource
	private SysuserServer userService;
	@Resource
	private PicServer PetService;
	

	
//	分页查询个人留言信息
	@RequestMapping("bbsList.do")
	public String bbsList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session){
		Sysuser u=(Sysuser)session.getAttribute("user");
		if(u==null||u.equals("")){
			return "login";
		}else{
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap=new HashMap<String,Object>();
		Map<String, Object> umap=new HashMap<String,Object>();
		Map<String, Object> fmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		pmap.put("uid", u.getId());
		umap.put("uid", u.getId());
		fmap.put("utype", "医生");
		int total=bbsService.getCount(umap);
		pageBean.setTotal(total);
		List<BbsWithBLOBs> list=bbsService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		map.put("ulist", userService.getAll(fmap));
		return "myBbsList";
		}
	}

	@RequestMapping("deleteBbs.do")
	public String deleteBbs(int id){
		bbsService.delete(id);
		return "redirect:bbsList.do";
	}
	
	//用户给医生留言
		@RequestMapping("addBbs.do")
		public String addBbs(HttpServletRequest request,BbsWithBLOBs bbs,HttpSession session){
			Sysuser u=(Sysuser)session.getAttribute("user");
			Timestamp time=new Timestamp(System.currentTimeMillis());
			if(u==null){
				return "login";
			}else{
				bbs.setUid(u.getId());
				bbs.setSta("待回复");
				bbs.setPubtime(time.toString().substring(0, 19));
				bbsService.add(bbs);
				return "redirect:bbsList.do";
			}

		}


//	回复建议反馈
	@RequestMapping("admin/addBbs.do")
	public String addOrder(HttpServletRequest request,BbsWithBLOBs bbs,HttpSession session){
		Sysuser u=(Sysuser)session.getAttribute("auser");
		if(u==null){
			return "admin/login";
		}else{
			bbs.setDid(u.getId());
			bbs.setSta("已回复");
			  bbsService.update(bbs);
			return "admin/success";
		}
	}
	
	
	@RequestMapping("admin/doUpdateBbs.do")
	public String doUpdateBbs(HttpServletRequest request,int id,ModelMap map){
		   map.put("bbs", bbsService.getById(id));
		   return "admin/update_bbs";
	}
	
	

//	分页查询  留言信息的列表
	@RequestMapping("admin/bbsList.do")
	public String bbsList2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session){
		Sysuser user =(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			Map<String, Object> umap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			/*pmap.put("did", user.getUid());
			bmap.put("did", user.getUid());*/
			umap.put("utype", "用户");
			int total=bbsService.getCount(bmap);
			pageBean.setTotal(total);
			List<BbsWithBLOBs> list=bbsService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(umap);
			String aa="dddd";
			aa.length();
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			session.setAttribute("p", 1);
			return "admin/list_bbs";
			
		}
		
	}

	@RequestMapping("admin/deleteBbs.do")
	public String deleteBbs2(int id){
		bbsService.delete(id);
		return "admin/success";
	}
}
