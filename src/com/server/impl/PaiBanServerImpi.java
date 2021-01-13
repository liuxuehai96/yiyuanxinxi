package com.server.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.PaiBanMapper;
import com.entity.PaiBan;
import com.server.PaiBanServer;
@Service
public class PaiBanServerImpi implements PaiBanServer {
   @Resource
   private PaiBanMapper gdao;
	@Override
	public int add(PaiBan po) {
		return gdao.insert(po);
	}

	@Override
	public int update(PaiBan po) {
		return gdao.updateByPrimaryKeySelective(po);
	}

	@Override
	public int delete(int id) {
		return gdao.deleteByPrimaryKey(id);
	}

	@Override
	public List<PaiBan> getAll(Map<String, Object> map) {
		return gdao.getAll(map);
	}

	@Override
	public PaiBan checkUname(String account) {
		return null;
	}

	@Override
	public List<PaiBan> getByPage(Map<String, Object> map) {
		return gdao.getByPage(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return gdao.getCount(map);
	}

	@Override
	public List<PaiBan> select(Map<String, Object> map) {
		return gdao.select(map);
	}

	@Override
	public PaiBan getById(int id) {
		return gdao.selectByPrimaryKey(id);
	}

}
