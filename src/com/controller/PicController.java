package com.controller;

import java.io.File;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.entity.Forder;
import com.entity.Pic;
import com.entity.Sysuser;
import com.server.FtypeServer;
import com.server.PicServer;
import com.server.SysuserServer;
import com.server.ForderServer;
import com.util.PageBean;

@Controller
public class PicController {
	@Resource
	private PicServer picService;
	@Resource
	private FtypeServer typeService;
	@Resource
	private ForderServer orderService;
	@Resource
	private SysuserServer userService;

	// 分页查询--显示的影像信息
	@RequestMapping("picList.do")
	public String showjianList(@RequestParam(value = "page", required = false) String page, ModelMap map,
			HttpSession session) {
		Sysuser user = (Sysuser) session.getAttribute("user");
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<String, Object>();
		pmap.put("uid", user.getId());
		cmap.put("uid", user.getId());
		int total = picService.getCount(cmap);
		pageBean.setTotal(total);
		List<Pic> list = picService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		map.put("tlist", typeService.getAll(null));
		session.setAttribute("p", 1);
		return "piclist";
	}
	
	
	// 分页模糊查询
		@RequestMapping("showpic.do")
		public String showpic(@RequestParam(value = "page", required = false) String page, ModelMap map,
				HttpSession session, Pic cd) {
			if (page == null || page.equals("")) {
				page = "1";
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
			Map<String, Object> pmap = new HashMap<String, Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());

			Map<String, Object> cmap = new HashMap<String, Object>();
			System.out.println("name===" + cd.getName());
			if (cd.getName() != null && !cd.getName().equals("")) {
				cmap.put("name", cd.getName());
				pmap.put("name", cd.getName());

			}
			if (cd.getFid() != null && !cd.getFid().equals("")) {
				cmap.put("fid", cd.getFid());
				pmap.put("fid", cd.getFid());
			}
			int total = picService.getCount(cmap);
			pageBean.setTotal(total);
			List<Pic> list = picService.getByPage(pmap);
			map.put("page", pageBean);
			map.put("list", list);
			session.setAttribute("p", 1);
			map.put("adlist", typeService.getAll(null));
			return "fwlist";
		}


	/**
	 * ===后台管理影像信息
	 * 
	 * @param map
	 * @param session
	 * @return
	 */
	// 处理添加影像的信息
	@RequestMapping("admin/doAddPic.do")
	public String doAddPic(ModelMap map, HttpSession session, int fid) {
		Map<String, Object> pmap = new HashMap<>();
		Forder order = orderService.getById(fid);
		Sysuser user = userService.getById(order.getUid());
		Sysuser ys = userService.getById(order.getFwid());
		pmap.put("fid", fid);
		List<Pic> plist = picService.getAll(pmap);
		map.put("order", order);
		map.put("user", user);
		map.put("ys", ys);
		map.put("plist", plist);
		map.put("tlist", typeService.getAll(null));
		return "admin/pic_add";
	}

	// 添加影像信息
	@RequestMapping("admin/addPic.do")
	public String addpic(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			Pic pic, String img, HttpSession session) {
		System.out.println("ddddd");
		Sysuser u = (Sysuser) session.getAttribute("auser");
		System.out.println("u=-=" + u);
		img = fileUpload(file, request, img);
		if (img == null || img.equals("")) {
			pic.setImg("zanwu.jpg");
		} else {
			pic.setImg(img);
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		pic.setPubtime(time.toString().substring(0, 19));
		pic.setIsdel("1");
		picService.add(pic);
		return "redirect:picList.do";
	}

	// 处理更新影像的信息
	@RequestMapping("admin/doUpdatePic.do")
	public String doUpdatepic(ModelMap map, int id) {
		Pic pic = picService.getById(id);
		map.put("pic", pic);
		map.put("tlist", typeService.getAll(null));
		Sysuser user = userService.getById(Integer.parseInt(pic.getUid()));
		Sysuser ys = userService.getById(pic.getYsid());
		map.put("user", user);
		map.put("ys", ys);
		return "admin/pic_update";
	}

	@RequestMapping("admin/updatePic.do")
	public String updatePic(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, Pic pic, String img) {
		img = fileUpload(file, request, img);
		if (img != null && !img.equals("")) {
			pic.setImg(img);
		}
		picService.update(pic);
		return "redirect:picList.do";
	}

	
	@RequestMapping("admin/picList.do")
	public String picList(@RequestParam(value = "page", required = false) String page, ModelMap map,
			HttpSession session) {
		Sysuser user = (Sysuser) session.getAttribute("auser");
		if (user == null) {
			return "admin/login";
		} else {
			if (page == null || page.equals("")) {
				page = "1";
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
			Map<String, Object> pmap = new HashMap<String, Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());
			Map<String, Object> cmap = new HashMap<String, Object>();
			Map<String, Object> bmap = new HashMap<String, Object>();
			pmap.put("ysid", user.getId());
			cmap.put("ysid", user.getId());
			bmap.put("ysid", user.getId());
			int total = picService.getCount(cmap);
			pageBean.setTotal(total);
			List<Pic> list = picService.getByPage(pmap);
			map.put("page", pageBean);
			map.put("list", list);
			map.put("tlist", typeService.getAll(null));
			map.put("ulist", userService.getAll(null));
			map.put("olist", orderService.getAll(null));
			map.put("blist", picService.getAll(bmap));
			session.setAttribute("p", 1);
			return "admin/pic_list";

		}

	}

	// 分页模糊查询
	@RequestMapping("admin/selectPicList.do")
	public String selectPicList(@RequestParam(value = "page", required = false) String page, ModelMap map,
			HttpSession session, Pic cd) {
		Sysuser user = (Sysuser) session.getAttribute("auser");
		if (user == null) {
			return "admin/login";
		} else {
			if (page == null || page.equals("")) {
				page = "1";
			}
			PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
			Map<String, Object> pmap = new HashMap<String, Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", pageBean.getPageSize());

			Map<String, Object> cmap = new HashMap<String, Object>();
			Map<String, Object> bmap = new HashMap<String, Object>();
			System.out.println("name===" + cd.getName());
			if (cd.getName() != null && !cd.getName().equals("")) {
				cmap.put("name", cd.getName());
				pmap.put("name", cd.getName());
			}
			if (cd.getCtype() != null && !cd.getCtype().equals("")) {
				cmap.put("ctype", cd.getCtype());
				pmap.put("ctype", cd.getCtype());
			}
			if (cd.getUid() != null && !cd.getUid().equals("")) {
				cmap.put("uid", cd.getUid());
				pmap.put("uid", cd.getUid());
			}
			pmap.put("ysid", user.getId());
			cmap.put("ysid", user.getId());
			bmap.put("ysid", user.getId());
			int total = picService.getCount(cmap);
			pageBean.setTotal(total);
			List<Pic> list = picService.getByPage(pmap);
			map.put("page", pageBean);
			map.put("list", list);
			session.setAttribute("p", 2);
			map.put("tlist", typeService.getAll(null));
			map.put("ulist", userService.getAll(null));
			map.put("olist", orderService.getAll(null));
			map.put("blist", picService.getAll(bmap));
			return "admin/pic_list";

		}
	}

	@RequestMapping("admin/deletepic.do")
	public String deletepic(int id) {
		picService.delete(id);
		return "redirect:picList.do";
	}

	// 文件上传
	public String fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, String img) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pa = request.getContextPath() + "/upload/" + fileName;
		System.out.println("path===" + pa);
		if (fileName != null && !fileName.equals("")) {
			img = fileName;
		} else {
			img = "zanwu.jpg";
		}
		return img;

	}
}
