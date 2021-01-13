package com.controller;

import java.io.File;



import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.entity.Sysuser;
import com.entity.Forder;
import com.entity.Ftype;
import com.entity.Pic;
import com.server.ForderServer;
import com.server.FtypeServer;
import com.server.PicServer;
import com.server.SysuserServer;
import com.util.Info;
import com.util.PageBean;

@Controller
public class ForderController {
	@Resource
	private ForderServer orderService;
	@Resource
	private SysuserServer userService;
	@Resource
	private FtypeServer ftypeService;
	@Resource
	private PicServer picService;
	


	//	预约、挂号
	@RequestMapping("addOrder.do")
	public String addOrder(HttpServletRequest request,Forder order,HttpSession session,int fwid,int stime){
		  Timestamp time=new Timestamp(System.currentTimeMillis());
		  Sysuser user=(Sysuser)session.getAttribute("user");
		  if(user==null){
			  return "login";
		  }else{
			  Map<String, Object> umap=new HashMap<>();
			  Map<String, Object> omap=new HashMap<>();
			  umap.put("uid", user.getId());
			  omap.put("id", fwid);
			  umap.put("ywid", order.getFwid());
			  umap.put("stime", stime);
			  umap.put("status", "预约");
			  int num=orderService.getCount(umap);
			  if(num>0){
				  return "error_yy";
			  }else{
				  Sysuser user1=userService.getById(fwid);
				  order.setFwid(fwid);
				  order.setFid(Integer.parseInt(user1.getFid()));
				  if(stime==11){
					 order.setEtime("星期一上午");
				  }
				  if(stime==12){
						 order.setEtime("星期一下午");
				  }
				  if(stime==21){
						 order.setEtime("星期二上午");
				  }
				  if(stime==22){
						 order.setEtime("星期二下午");
				  }
				  if(stime==31){
						 order.setEtime("星期三上午");
				  }
				  if(stime==32){
						 order.setEtime("星期三下午");
				  }
				  if(stime==41){
						 order.setEtime("星期四上午");
				  }
				  if(stime==42){
						 order.setEtime("星期四下午");
				  }
				  if(stime==51){
						 order.setEtime("星期五上午");
				  }
				  if(stime==52){
						 order.setEtime("星期五下午");
				  }
				  if(stime==61){
						 order.setEtime("星期六上午");
				  }
				  if(stime==62){
						 order.setEtime("星期六下午");
				  }
				  if(stime==71){
						 order.setEtime("星期天上午");
				  }
				  if(stime==72){
						 order.setEtime("星期天下午");
				  }
				  order.setUid(user.getId());
				  order.setPubtime(time.toString().substring(0,19));
				  order.setStatus("已预约");
				  order.setPrice(5+"");
				  order.setCno(Info.getID());
				  order.setIsdel("1");
				  orderService.add(order);
				  return "redirect:showAllOrder.do";
			  }
		  }
	}
	
	
	
	@RequestMapping("showOrderDetail.do")
	public String showOrderDetail(HttpServletRequest request,int id,ModelMap map,HttpSession session){
		  Forder order=orderService.getById(id);
		  Map<String, Object> tmap = new HashMap<>();
		  tmap.put("fid", id);
		  List<Pic> plist = picService.getAll(tmap);
		  Pic pic=picService.getById(order.getFid());
		  Sysuser user=userService.getById(order.getUid());
		  Sysuser doc=userService.getById(order.getFwid());
		  map.put("pic", pic);
		  map.put("user", user);
		  map.put("doc", doc);
		  map.put("order", order);
		  map.put("plist", plist);
		  map.put("flist", ftypeService.getAll(null));
		  return "orderx";
	}

//	分页查询个人预约信息
	@RequestMapping("showAllOrder.do")
	public String fjianList(@RequestParam(value="page",required=false)String page,
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
		int total=orderService.getCount(umap);
		pageBean.setTotal(total);
		List<Forder> list=orderService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		map.put("plist", picService.getAll(null));
		map.put("flist", ftypeService.getAll(null));
		map.put("ulist", userService.getAll(fmap));
		return "myorderlist";
		}
	}
	
	@RequestMapping("deleteForder.do")
	public String deleteForder2(int id){
		orderService.delete(id);
		return "redirect:showAllOrder.do";
	}
	
	/**
	 * ===后台管理===
	 * @param page
	 * @param map
	 * @param session
	 * @param yp
	 * @return
	 */
	
//   分页查询---就诊记录--预约挂号
	@RequestMapping("admin/forderList.do")
	public String forderList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "预约");
			pmap.put("status", "预约");
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			System.out.println("blist==="+blist.size());
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 1);
			return "admin/list_order";
		}
		
	}
	@RequestMapping("admin/orderListSearch.do")
	public String orderListSearch(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "预约");
			pmap.put("status", "预约");
			System.out.println("fid===="+yp.getFid());
			if(yp.getUid()!=null&&!yp.getUid().equals("")){
				cmap.put("uid", yp.getUid());
				pmap.put("uid", yp.getUid());
			}
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 2);
			return "admin/list_order";
		}
		
	}
	
	
//	通过审核
	@RequestMapping("admin/tongGuo.do")
	public String tongGuo(int id,Forder order){
		order=orderService.getById(id);
		order.setStatus("待诊断");
		order.setId(id);
		orderService.update(order);
		return "admin/success";
	}
//	不通过审核
	@RequestMapping("admin/bTongGuo.do")
	public String bLuQu(int id,Forder yp){
		yp.setStatus("预约失败");
		yp.setId(id);
		orderService.update(yp);
		return "admin/success";
	}
	
	@RequestMapping("admin/forderList_zd.do")
	public String forderList_zd(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "诊断");
			pmap.put("status", "诊断");
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			System.out.println("blist==="+blist.size());
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 1);
			return "admin/list_order_zd";
		}
		
	}
	
	
	@RequestMapping("admin/orderList_zdSearch.do")
	public String orderList_zdSearch(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "诊断");
			pmap.put("status", "诊断");
			System.out.println("fid===="+yp.getFid());
			if(yp.getUid()!=null&&!yp.getUid().equals("")){
				cmap.put("uid", yp.getUid());
				pmap.put("uid", yp.getUid());
			}
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 2);
			return "admin/list_order_zd";
		}
		
	}
	
	
//	处理添加就诊结果
	@RequestMapping("admin/doAddpj.do")
	public String doAddpj(int id,ModelMap map){
		Forder order=orderService.getById(id);
		Map<String, Object> tmap = new HashMap<>();
		tmap.put("fid", id);
		List<Pic> plist = picService.getAll(tmap);
		Sysuser user=userService.getById(order.getUid());
		Sysuser ys=userService.getById(order.getFwid());
		map.put("plist", plist);
		map.put("user", user);
		map.put("ys", ys);
		map.put("order", order);
		return "admin/pj";
	}
//	添加就诊结果
	@RequestMapping("admin/addpj.do")
	public String addpj(ModelMap map,Forder order){
		order.setStatus("历史记录");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		order.setEtime(time.toString().substring(0, 19));
		orderService.update(order);
		return "admin/success";
	}
	
	
	//查看历史记录
	@RequestMapping("admin/liShi.do")
	public String liShi(ModelMap map,HttpSession session,Forder yp,int id){
		Forder order=orderService.getById(id);
		Map<String, Object> tmap = new HashMap<>();
		tmap.put("fid", id);
		List<Pic> plist = picService.getAll(tmap);
		Sysuser user=userService.getById(order.getUid());
		Sysuser ys=userService.getById(order.getFwid());
		map.put("plist", plist);
		map.put("user", user);
		map.put("ys", ys);
		map.put("order", order);
		return "admin/lisi";
	}
	@RequestMapping("admin/forderList_ls.do")
	public String forderList_ls(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "历史记录");
			pmap.put("status", "历史记录");
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			System.out.println("blist==="+blist.size());
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 1);
			return "admin/list_order_ls";
		}
		
	}
	@RequestMapping("admin/orderList_lsSearch.do")
	public String orderList_lsSearch(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Forder yp){
		Sysuser user=(Sysuser)session.getAttribute("auser");
		if(user==null){
			return "admin/login";
		}else{
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),PageBean.PAGESIZE);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap=new HashMap<String,Object>();
			Map<String, Object> bmap=new HashMap<String,Object>();
			cmap.put("status", "历史记录");
			pmap.put("status", "历史记录");
			System.out.println("fid===="+yp.getFid());
			if(yp.getUid()!=null&&!yp.getUid().equals("")){
				cmap.put("uid", yp.getUid());
				pmap.put("uid", yp.getUid());
			}
			cmap.put("fwid", user.getId());
			pmap.put("fwid", user.getId());
			bmap.put("fwid", user.getId());
			int total=orderService.getCount(cmap);
			pageBean.setTotal(total);
			List<Forder> list=orderService.getByPage(pmap);
			List<Sysuser> ulist=userService.getAll(null);
			List<Forder> blist=orderService.getAll(bmap);
			List<Ftype> flist=ftypeService.getAll(null);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("ulist", ulist);
			map.put("flist", flist);
			map.put("blist", blist);
			session.setAttribute("p", 2);
			return "admin/list_order_ls";
		}
		
	}
	
	@RequestMapping("admin/deleteForder.do")
	public String deleteForder(int id){
		orderService.delete(id);
		return "admin/success";
	}

//	文件上传
	public String fileUpload(@RequestParam(value="file",required=false)MultipartFile file,
			HttpServletRequest request,String img){
		String path=request.getSession().getServletContext().getRealPath("upload");
		System.out.println("path==="+path);
		System.out.println("file==="+file);
		String fileName=file.getOriginalFilename();
		System.out.println("fileName==="+fileName);
		File targetFile=new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pa=request.getContextPath()+"/upload/"+fileName;
		System.out.println("path==="+pa);
		if(fileName!=null&&!fileName.equals("")){
			img=fileName;
		}else{
			img=null;	
		}
		
		return img;
		
	}
}
