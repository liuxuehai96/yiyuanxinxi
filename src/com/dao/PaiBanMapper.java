package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.PaiBan;

public interface PaiBanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaiBan record);

    int insertSelective(PaiBan record);

    PaiBan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaiBan record);

    int updateByPrimaryKey(PaiBan record);
    
    public PaiBan checkUname(Map<String, Object> uname);
//  查询所有信息
  public List<PaiBan> getAll(Map<String, Object> map);
//  获取条数
  public int getCount(Map<String, Object> po); 
//  分页
  public List<PaiBan> getByPage(Map<String, Object> map);
//  模糊查询并分页
  public List<PaiBan> select(Map<String, Object> map);
}