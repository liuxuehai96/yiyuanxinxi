package com.server;

import java.util.List;

import java.util.Map;

import com.entity.PaiBan;

public interface PaiBanServer {
//  添加
  public int add(PaiBan po);
//  修改
  public int update(PaiBan po);
//  删除
  public int delete(int id);
//  查询
  public List<PaiBan> getAll(Map<String, Object> map);
//  验证
  public PaiBan checkUname(String account);
//  获取对象
  public PaiBan getById( int id);
//  分页显示
  public List<PaiBan> getByPage(Map<String, Object> map);
//  获取信息的条数
  public int getCount(Map<String, Object> map);
//  模糊查询
  public List<PaiBan> select(Map<String, Object> map);
}
