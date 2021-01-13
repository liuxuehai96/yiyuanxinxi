package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.entity.PaiBan;
import com.entity.Sysuser;
import com.server.FtypeServer;
import com.server.PaiBanServer;
import com.server.SysuserServer;
import com.util.PageBean;

import net.sf.json.JSONObject;

@Controller
public class SysuserController {
	@Resource
	private SysuserServer userService;
	@Resource
	private FtypeServer ftypeService;
	@Resource
	private PaiBanServer paiBanService;

	/**
	 * ===前台信息管理===
	 * 
	 * @param page
	 * @param session
	 * @param map
	 * @return
	 */
	// 查询所有医生的信息
	@RequestMapping("doctor.do")
	public String doctor(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "医生");
		cmap.put("utype", "医生");
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		map.put("flist", ftypeService.getAll(null));
		return "docList";
	}

	// 查询所有医生的信息--搜索
	@RequestMapping("searchDoctor.do")
	public String searchDoctor(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Sysuser user) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("tname", user.getTname());
		cmap.put("tname", user.getTname());
		pmap.put("utype", "医生");
		cmap.put("utype", "医生");
		System.out.println(cmap);
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		map.put("flist", ftypeService.getAll(null));
		return "docList";
	}
	//科室医生
		@RequestMapping("yiShengTypeList.do")
	public String yiShengTypeList(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Sysuser user,int fid) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("fid", fid);
		pmap.put("utype", "医生");
		List<Sysuser> list = userService.getByPage(pmap);
		//pageBean = list.size();
		map.put("list", list);
		session.setAttribute("p", 2);
		map.put("flist", ftypeService.getAll(null));
		return "docList";
	}

	// 医生信息
	@RequestMapping("showUser.do")
	public String showUser(ModelMap map, HttpSession session, HttpServletRequest request, int id) {
		map.put("user", userService.getById(id));
		Map<String, Object> umap =new HashMap<>();
		umap.put("uid", id);
		List<PaiBan> plist = paiBanService.getAll(umap);
		if(plist.size()>0){
			map.put("paiBan", plist.get(0));
		}else{
			map.put("paiBan", null);
		}
		int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
		Date date=new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		int wd = weekDays[w];
		map.put("fweek", wd);
		int hour=cal.get(Calendar.HOUR_OF_DAY);//小时
		System.out.println(hour);
		map.put("fhour", hour);
		map.put("flist", ftypeService.getAll(null));
		return "docx";
	}

	// 前台登录
	@RequestMapping("login.do")
	public String checkAdminLogin(Sysuser user, HttpSession session) {
		Map<String, Object> u = new HashMap<String, Object>();
		System.out.println("name===" + user.getUname());
		u.put("uname", user.getUname());
		u.put("pwd", user.getPwd());
		u.put("utype", "用户");
		user = userService.adminLogin(u);
		if (user != null) {
			session.setAttribute("user", user);
			System.out.println("user=" + user);
			session.removeAttribute("suc");
			return "redirect:index.do";
		} else {
			session.setAttribute("suc", "登录失败！用户名或密码错误！");
			return "login";
		}

	}

	// 前台忘记密码
	@RequestMapping("forPwd.do")
	public String forPwd(Sysuser user, ModelMap map) {
		Map<String, Object> umap = new HashMap<String, Object>();
		umap.put("uname", user.getUname());
		Sysuser u = userService.checkUname(umap);
		if (u == null || u.equals("")) {
			return "error_uname";
		} else {
			map.put("user", u);
			return "fore_losspwd";
		}

	}


	// 处理修改个人信息
	@RequestMapping("showInfo.do")
	public String showInfo(HttpSession session, ModelMap map) {
		Sysuser u = (Sysuser) session.getAttribute("user");
		if (u == null) {
			return "login";
		} else {
			map.put("user", userService.getById(u.getId()));
			return "showUserinfo";
		}
	}

	// 处理修改个人信息
	@RequestMapping("addShowInfo.do")
	public String addShowInfo(HttpSession session, ModelMap map, Sysuser user) {
		userService.update(user);
		return "success";
	}
	
	// 前台注销登录
	@RequestMapping("loginout.do")
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("p");
		return "login";
	}

	// 后台登录
	@RequestMapping("admin/alogin.do")
	public String checkLogin(Sysuser user, HttpSession session) {
		Map<String, Object> u = new HashMap<String, Object>();
		System.out.println("name===" + user.getUname());
		System.out.println("pwd===" + user.getPwd());
		u.put("uname", user.getUname());
		u.put("utype", user.getUtype());
		u.put("pwd", user.getPwd());
		user = userService.adminLogin(u);
		if (user != null) {
			session.setAttribute("auser", user);
			System.out.println("auser=" + user);
			return "admin/index";
		} else {
			return "admin/login";
		}
	}

	// 后台注销登录
	@RequestMapping("admin/loginout.do")
	public String adminLoginOut(HttpSession session) {
		session.removeAttribute("auser");
		return "admin/login";
	}



	// 验证用户名是否存在
	@RequestMapping("admin/checkUname.do")
	public void checkUname(Sysuser user, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uname", user.getUname());
		System.out.println("uname===" + user.getUname());
		System.out.println("uname222===" + userService.checkUname(map));
		JSONObject obj = new JSONObject();
		if (userService.checkUname(map) != null) {

			obj.put("info", "ng");
		} else {
			obj.put("info", "用户名可以用！");

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

	@RequestMapping("checkmibao.do")
	public void checkMB(Sysuser user, HttpServletResponse response, HttpSession session) {
		JSONObject obj = new JSONObject();
		Sysuser u = userService.getById(user.getId());
		String q = u.getQuestion();
		if (u == null || u.equals("")) {
			obj.put("info", "ng");
		} else {
			if (q.equals(user.getQuestion())) {
				obj.put("info", u.getPwd());
			} else {
				obj.put("info", "ng");
			}
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

	// 验证用户名是否存在
	@RequestMapping("checkUname.do")
	public void checkReg(Sysuser user, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("uname", user.getUname());
		System.out.println("uname===" + user.getUname());
		System.out.println("uname222===" + userService.checkUname(map));
		JSONObject obj = new JSONObject();
		if (userService.checkUname(map) != null) {
			System.out.println("uname233333333333===");
			obj.put("info", "ng");
		} else {
			System.out.println("uname255555555555555===");
			obj.put("info", "用户名可以用！");
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

	@RequestMapping("checkPass.do")
	public void checkPass(Sysuser user, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("uname", user.getUname());
		System.out.println("uname===" + user.getUname());
		System.out.println("uname222===" + userService.checkUname(map));
		JSONObject obj = new JSONObject();
		if (userService.checkUname(map) != null) {
			obj.put("info", userService.checkUname(map).getPwd());
		} else {
			obj.put("info", "ng");

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

	@RequestMapping("reg.do")
	public String addReg(Sysuser user, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("uname", user.getUname());
		if (userService.checkUname(map) != null) {
			System.out.println("uname233333333333===");
			return "regist";
		} else {
			user.setIsdel("1");
			user.setUtype("用户");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			user.setPubtime(time.toString().substring(0, 19));
			userService.add(user);
			return "login";
		}
	}

	/**
	 * ===后台管理===
	 * 
	 * @param user
	 * @param session
	 * @return
	 */

	@RequestMapping("admin/showUserInfo.do")
	public String showUserInfo(ModelMap map, HttpSession session) {
		if (session.getAttribute("auser") == null) {
			return "admin/login";
		}
		Sysuser u = (Sysuser) session.getAttribute("auser");
		map.put("user", userService.getById(u.getId()));
		return "admin/update_user_persion";
	}

	@RequestMapping("admin/updatePersionUser.do")
	public String updateUserInfo(ModelMap map, HttpSession session, Sysuser user) {
		userService.update(user);
		map.put("user", userService.getById(user.getId()));
		session.setAttribute("suc", "cc");
		return "redirect:showUserInfo.do";
	}

	// 添加用户

	// 查询所有用户的信息
	@RequestMapping("admin/userList0.do")
	public String userList0(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "管理员");
		cmap.put("utype", "管理员");
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "admin/user_list0";
	}
	// 处理更新用户的信息
	@RequestMapping("admin/doUpdateUser.do")
	public String doUpdateUser(ModelMap map, int id) {
		System.out.println("id==" + id);
		map.put("user", userService.getById(id));
		return "admin/update_user";
	}

	// 更新用户的信息
	@RequestMapping("admin/updateUser.do")
	public String updateUser(Sysuser user) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setPubtime(time.toString().substring(0,19));
		userService.update(user);
		return "admin/success";
	}

	@RequestMapping("admin/addUser.do")
	public String addUser(Sysuser user, HttpSession session) {
		user.setIsdel("1");
		user.setUtype("用户");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setPubtime(time.toString().substring(0,19));
		userService.add(user);
		return "redirect:userList.do";
	}

	// 查询所有用户的信息
	@RequestMapping("admin/userList.do")
	public String userList(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "用户");
		cmap.put("utype", "用户");
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "admin/user_list";
	}

	// 模糊查询并分页
	@RequestMapping("admin/userListQuery0.do")
	public String useListQuery0(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Sysuser user) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		pmap.put("uname", user.getUname());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "管理员");
		cmap.put("utype", "管理员");
		int total = userService.getCount(pmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		return "admin/user_list0";
	}

	// 模糊查询并分页
	@RequestMapping("admin/userListQuery.do")
	public String useListQuery(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Sysuser user) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		pmap.put("uname", user.getUname());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "用户");
		cmap.put("utype", "用户");
		int total = userService.getCount(pmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		return "admin/user_list";
	}

	/**
	 * ===医生管理===
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	// 处理添加医生
	@RequestMapping("admin/doAddUser_ys.do")
	public String doAddUser_ys(ModelMap map) {
		map.put("flist", ftypeService.getAll(null));
		return "admin/add_user_ys";
	}

	// 管理员添加医生信息
	@RequestMapping("admin/addUser_ys.do")
	public String addUser_ys(Sysuser user, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file, String img) {
		user.setIsdel("1");
		System.out.println("img==" + img);
		img = fileUpload(file, request, img);
		System.out.println("img=333=" + img);
		user.setImg(img);
		user.setUtype("医生");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setPubtime(time.toString().substring(0, 19));
		userService.add(user);
		return "redirect:userList_ys.do";
	}

	// 更新医生的信息
	@RequestMapping("admin/doUpdateUser_ys.do")
	public String doUpdateUser2(ModelMap map, int id) {
		System.out.println("id==" + id);
		map.put("flist", ftypeService.getAll(null));
		map.put("user", userService.getById(id));
		return "admin/update_user_ys";
	}

	// 保存更新后的医生信息updateUser2.do
	@RequestMapping("admin/updateUser_ys.do")
	public String updateUser2(Sysuser user, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file, String img) {
		System.out.println("img==" + img);
		img = fileUpload(file, request, img);
		System.out.println("img=333=" + img);
		if (!img.equals("zanwu.jpg")) {
			user.setImg(img);
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setPubtime(time.toString().substring(0, 19));
		userService.update(user);
		return "redirect:userList_ys.do";
	}



	// 查询所有医生的信息
	@RequestMapping("admin/userList_ys.do")
	public String userList_ys(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		pmap.put("utype", "医生");
		cmap.put("utype", "医生");
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		map.put("flist", ftypeService.getAll(null));
		session.setAttribute("p", 1);
		return "admin/user_list_ys";
	}

	// 模糊查询并分页--医生
	@RequestMapping("admin/userList_ysQuery.do")
	public String userList_ysQuery(@RequestParam(value = "page", required = false) String page, HttpSession session,
			ModelMap map, Sysuser user) {
		if (page == null || page.equals("")) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), PageBean.PAGESIZE);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", pageBean.getPageSize());
		Map<String, Object> cmap = new HashMap<>();
		if (user.getTname() != null && !user.getTname().equals("")) {
			pmap.put("tname", user.getTname());
			cmap.put("tname", user.getTname());
		}
		if (user.getUname() != null && !user.getUname().equals("")) {
			pmap.put("uname", user.getUname());
			cmap.put("uname", user.getUname());
		}
		pmap.put("utype", "医生");
		cmap.put("utype", "医生");
		int total = userService.getCount(cmap);
		pageBean.setTotal(total);
		List<Sysuser> list = userService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 2);
		map.put("flist", ftypeService.getAll(null));
		return "admin/user_list_ys";
	}

	@RequestMapping("admin/deleteUser.do")
	public String deleteUser(int id) {
		userService.delete(id);
		return "admin/success";
	}

	// 文件上传
	public String fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, String img) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		System.out.println("path===" + path);
		System.out.println("file===" + file);
		String fileName = file.getOriginalFilename();
		System.out.println("fileName===" + fileName);
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
