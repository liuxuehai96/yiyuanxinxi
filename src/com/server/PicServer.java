package com.server;

import java.util.List;

import java.util.Map;

import com.entity.Pic;

public interface PicServer {
//  添加
  public int add(Pic po);
//  修改
  public int update(Pic po);
//  删除
  public int delete(int id);
//  查询
  public List<Pic> getAll(Map<String, Object> map);
//  验证
  public Pic checkUname(String account);
//  获取对象
  public Pic getById( int id);
//  分页显示
  public List<Pic> getByPage(Map<String, Object> map);
//  获取信息的条数
  public int getCount(Map<String, Object> map);
//  模糊查询
  public List<Pic> select(Map<String, Object> map);
}
