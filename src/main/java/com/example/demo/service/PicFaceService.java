package com.example.demo.service;

import com.example.demo.controller._Base64PicController;
import com.example.demo.entity.PicFace;
import com.example.demo.repository.PicFaceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service("picfaceService")
public class PicFaceService {
  @Autowired
  PicFaceRep picFaceRep;



  public void add(PicFace picFace){
    picFaceRep.add(picFace);
  }

  public List<Long> getPublicPic(Long faceId){
    return getPublicPic(faceId,1);
  }
  public List<Long> getPublicPic(Long faceId,int rank){
    List<PicFace> picFaceList = picFaceRep.findByFace(faceId, true, rank);
    List<Long> list = null;
    for(PicFace pic : picFaceList){
      list.add(pic.getPicId());
    }
    return list;
  }

  public List<Long> getPic(Long faceId){
    return getPic(faceId,1);
  }
  public List<Long> getPic(Long faceId,int rank){
    List<PicFace> byFace = picFaceRep.findByFace(faceId,false,rank);
    List<Long> picIdList = null;
    for(PicFace pic:byFace){
      picIdList.add(pic.getPicId());
    }
    return picIdList;
  }

  public List<Long> delete(Long picId){
    List<PicFace> picFaces = picFaceRep.findByPic(picId);
    List<Long> facelist = new LinkedList<>();
    for(PicFace picFace:picFaces){
      facelist.add(picFace.getFaceId());
      picFaceRep.deleteById(picFace.getId());
    }
    return facelist;
  }


  public List<Long> findSimilar(List<Long> faceIdlist, boolean ispublic, double confidence) {
    Set<Long> pictures = new HashSet<>();
    for(Long faceId:faceIdlist){
      List<Long> similar = findSimilar(faceId, ispublic, confidence);
      pictures.addAll(similar);
    }
    return new LinkedList<Long>(pictures);
  }
  public  List<Long> findSimilar(Long faceId,boolean ispublic,double confidence){
    List<PicFace> byFace = picFaceRep.findByFace(faceId, ispublic, confidence);
    Set<Long> pictures = new HashSet<>();
    for(PicFace picFace:byFace){
      pictures.add(picFace.getPicId());
    }
    return new LinkedList<Long>(pictures);
  }
}
