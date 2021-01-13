package com.server.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.PicMapper;
import com.entity.Pic;
import com.server.PicServer;
@Service
public class PicServerImpi implements PicServer {
   @Resource
   private PicMapper gdao;
	@Override
	public int add(Pic po) {
		return gdao.insert(po);
	}

	@Override
	public int update(Pic po) {
		return gdao.updateByPrimaryKeySelective(po);
	}

	@Override
	public int delete(int id) {
		return gdao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Pic> getAll(Map<String, Object> map) {
		return gdao.getAll(map);
	}

	@Override
	public Pic checkUname(String account) {
		return null;
	}

	@Override
	public List<Pic> getByPage(Map<String, Object> map) {
		return gdao.getByPage(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return gdao.getCount(map);
	}

	@Override
	public List<Pic> select(Map<String, Object> map) {
		return gdao.select(map);
	}

	@Override
	public Pic getById(int id) {
		return gdao.selectByPrimaryKey(id);
	}

}
