package com.example.demo.repository;

import com.example.demo.entity.PicFace;

import java.util.List;

public interface PicFaceRep {
  boolean add(PicFace picFace);
  boolean findExistById(Long Id);
  boolean findExistById(Long faceId,Long picId);
  PicFace findById(Long Id);
  boolean deleteById(Long Id);

  /**
   * 不存在返回空列表
   * @param faceId
   * @return
   */
  /**
   * 从人脸找图片需要分公有和私有
   * @param faceId
   * @return
   */
  /**
   * 找图片需要分类似程度
   * @param faceId
   * @return
   */
  List<PicFace> findByFace(Long faceId);
  List<PicFace> findByFace(Long faceId,boolean ispublic);
  List<PicFace> findByFace(Long faceId,boolean ispublic,double confidence);

  /**
   * 从picid找的话自然可以找到是共有还是私有
   * @param picId
   * @return
   */
  List<PicFace> findByPic(Long picId);
  List<PicFace> findByPic(Long picId,double confidence);
  void createTable();
}
