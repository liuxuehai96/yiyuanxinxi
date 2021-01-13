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

import com.entity.PaiBan;
import com.entity.Sysuser;
import com.server.FtypeServer;
import com.server.PaiBanServer;
import com.server.SysuserServer;
import com.util.PageBean;

@Controller
public class PaiBanController {
	@Resource
	private PaiBanServer paiBanService;
	@Resource
	private FtypeServer typeService;
	@Resource
	private SysuserServer sysuserService;

	/**
	 * 后台排班的处理
	 * 
	 * @param file
	 * @param request
	 * @param img
	 * @return
	 */

	// 处理添加排班的信息
	@RequestMapping("admin/doAddPaiBan.do")
	public String doAddPaiBan_admin(ModelMap map, HttpSession session) {
		Map<String, Object> umap = new HashMap<>();
		umap.put("utype", "医生");
		map.put("ulist", sysuserService.getAll(umap));
		return "admin/paiBan_add";
	}

	// 添加排班信息
	@RequestMapping("admin/addPaiBan.do")
	public String addPaiBan_admin(HttpServletRequest request, PaiBan paiBan, HttpSession session) {
		Map<String, Object> tmap = new HashMap<>();
		tmap.put("uid", paiBan.getUid());
		int count = paiBanService.getCount(tmap);
		if (count >= 1) {
			session.setAttribute("info", "该医生已排班");
			return "admin/error_paiBan";
		} else {
			Sysuser user = sysuserService.getById(paiBan.getUid());
			paiBan.setStatus("在用");
			paiBan.setFid(Integer.parseInt(user.getFid()));
			Timestamp time = new Timestamp(System.currentTimeMillis());
			paiBan.setPubtime(time.toString().substring(0, 19));
			paiBanService.add(paiBan);
			return "redirect:paiBanList.do";
		}

	}

	// 处理更新排班的信息
	@RequestMapping("admin/doUpdatePaiBan.do")
	public String doUpdatePaiBan_admin(ModelMap map, int id) {
		Map<String, Object> umap = new HashMap<>();
		umap.put("utype", "医生");
		map.put("ulist", sysuserService.getAll(umap));
		map.put("paiBan", paiBanService.getById(id));
		return "admin/paiBan_update";
	}

	// 处理更新排班的信息
	@RequestMapping("admin/updatePaiBan.do")
	public String updatePaiBan_admin(HttpServletRequest request, PaiBan paiBan) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		paiBan.setPubtime(time.toString().substring(0, 19));
		paiBanService.update(paiBan);
		return "redirect:paiBanList.do";
	}

	// 删除排班
	@RequestMapping("admin/deletePaiBan.do")
	public String deletePaiBan_admin(ModelMap map, int id) {
		paiBanService.delete(id);
		return "redirect:paiBanList.do";
	}

	// 分页查询--显示所有的排班信息
	@RequestMapping("admin/paiBanList.do")
	public String paiBanList(@RequestParam(value = "page", required = false) String page, ModelMap map,
			HttpSession session) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<String, Object>();
		int total = paiBanService.getCount(cmap);
		pageBean.setTotal(total);
		List<PaiBan> list = paiBanService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		Map<String, Object> umap = new HashMap<>();
		umap.put("utype", "医生");
		map.put("ulist", sysuserService.getAll(umap));
		map.put("flist", typeService.getAll(null));
		session.setAttribute("p", 1);
		return "admin/paiBan_list";
	}

	// 分页查询--查询所有的排班信息
	@RequestMapping("admin/paiBanListQuery.do")
	public String paiBanList_search(@RequestParam(value = "page", required = false) String page, ModelMap map,
			HttpSession session, PaiBan paiBan) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<String, Object>();
		if (paiBan.getFid() != null && !paiBan.getFid().equals("")) {
			pmap.put("fid", paiBan.getFid());
			cmap.put("fid", paiBan.getFid());
		}
		if (paiBan.getUid() != null && !paiBan.getUid().equals("")) {
			pmap.put("uid", paiBan.getUid());
			cmap.put("uid", paiBan.getUid());
		}
		int total = paiBanService.getCount(cmap);
		pageBean.setTotal(total);
		List<PaiBan> list = paiBanService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		map.put("flist", typeService.getAll(null));
		Map<String, Object> umap = new HashMap<>();
		umap.put("utype", "医生");
		map.put("ulist", sysuserService.getAll(umap));
		session.setAttribute("p", 2);
		return "admin/paiBan_list";
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
