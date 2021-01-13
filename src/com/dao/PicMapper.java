package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Pic;

public interface PicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKeyWithBLOBs(Pic record);

    int updateByPrimaryKey(Pic record);
    
    public Pic checkUname(Map<String, Object> uname);
//  查询所有信息
  public List<Pic> getAll(Map<String, Object> map);
//  获取条数
  public int getCount(Map<String, Object> po); 
//  分页
  public List<Pic> getByPage(Map<String, Object> map);
//  模糊查询并分页
  public List<Pic> select(Map<String, Object> map);
}