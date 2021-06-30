package com.example.demo.repository;

import java.util.List;

public interface ImageClassficationRep {

  public static final String tableName = "imageClassify";

  /**
   * 获取一个用户的分类情况，公共相册为root根用户，创建时自动生成记录
   * @param owner
   * @return
   */
  public List<String> getCategy(String owner,boolean ispublic);


  /**
   * 增删数据，无法删除others,无法添加重复信息
   * 对于公共分类无法修改
   * @param owner
   * @param categy
   * @return
   */
  public boolean deleteInfo(String owner,String categy);

  public boolean addInfo(String owner,String categy);
  public boolean addInfo(String owner,String categy,String remark);
  public boolean addInfo(String owner,String categy,boolean ispublic,String remark);


  /**
   * 初次运行自动生成表格
   */
  public void createTable();

  /*
  增加公共相册的记录
   */
  public void initTable();

  public List<String> findPublicCategy();
}
