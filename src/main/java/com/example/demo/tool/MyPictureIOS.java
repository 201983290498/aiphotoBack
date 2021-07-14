package com.example.demo.tool;


import com.example.demo.AI.entity.tool.facedetect.FaceDetails;
import com.example.demo.entity.Picture;
import com.example.demo.entity._Base64Picture;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;


/**
 * 图片读写类
 */
@Data
public class MyPictureIOS {
  public static String path = "C:/Users/coder/Desktop/demo/src/main/resources/static";
  public static int number = 0;

  public InputStream InputStream;

  //获取文件读入流
  public FileInputStream readImage(String abspath) throws IOException {
    File file = new File(abspath);
//    System.out.println(file.exists());
    if (!file.exists()) {
      return null;
    } else {
      FileInputStream fileInputStream = new FileInputStream(file);
      return fileInputStream;
    }
  }

  //根据路径读取项目的bytes
  public byte[] readImageBytes(String abspath) throws IOException {
    File file = new File(abspath);
    return readImageByes(file);
  }
  public byte[] readImageByes(File file) {
    byte[] bytes = null;
    if (!file.exists()) {
      return null;
    } else {
      FileInputStream fileInputStream = null;
      try {
        fileInputStream = new FileInputStream(file);
        bytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return bytes;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return bytes;
  }


  //将字符流写成图片
  public void readBin2Image(Picture pictrue) {
    String dirName = path + "/" + pictrue.getOwner();
    String fileName = dirName + "/" + pictrue.getPicname();
    File file = new File(dirName);
    if (!file.exists()) {
      file.mkdir();
    }

    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      fileOutputStream.write(pictrue.getBinarypic(), 0, pictrue.getBinarypic().length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public MyPictureIOS() {
  }

  public MyPictureIOS(InputStream fileInputStream) {
    this.InputStream = fileInputStream;
  }

  /**
   * 在静态目录动态的创建图片的文件夹，每个用户一个文件夹，当更换类别的时候需要重新动态读写
   *
   * @param picture
   */
  public void InitDir(Picture picture) {
    String dirName = path + "/" + picture.getOwner();
    File file = new File(dirName);
//    System.out.println(file.exists());
    if (file.exists()) {
      deleteFile(file);
    }
  }

  private void deleteFile(File file) {
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      for (File file1 : files) {
        deleteFile(file1);
      }
    }
    file.delete();
  }

  /**
   * 读取base64的图片
   *
   * @param picString
   * @return
   */
  private String data2B64(String picString) {
    int index = picString.indexOf(",");
    return picString.substring(index + 1);
  }

  /**
   * 将前端base64的字符窜转换成bytes[]
   */
  public byte[] B64String2Bytes(String b64) {
    byte[] bytes = null;
    Base64.Decoder decoder = Base64.getDecoder();
    bytes = decoder.decode(data2B64(b64));
    for (int i = 0; i < bytes.length; i++) {
      if (bytes[i] < 0) bytes[i] += 256;
    }
    return bytes;
  }

  public byte[] B64String2Bytes(_Base64Picture pic) {
    return B64String2Bytes(pic.getB64());
  }

  public String getPicType(String b64){
    int start = b64.indexOf("/")+1;//读出图片的类型
    int end = b64.indexOf(";");
    return b64.substring(start,end);
  }
  public String getPicType(_Base64Picture pic){
    return getPicType(pic.getB64());
  }

  public void write(String path,byte[] bytes){
    FileImageOutputStream imageOutput = null;//打开输入流
    try {
      imageOutput = new FileImageOutputStream(new File(path));
      imageOutput.write(bytes, 0, bytes.length);//将byte写入硬盘
      imageOutput.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 已知原图劫取部分图片
   */
  private byte[] cutPNG(String b64,String path1,int x,int y,int width,int height) throws IOException {
    byte[] bytes = B64String2Bytes(b64);//将b64转换成bytes
    String type = getPicType(b64);
    path1 = path1+(++number) +"."+type;
    InputStream input = new ByteArrayInputStream(bytes);
    ImageInputStream imageInputStream = ImageIO.createImageInputStream(input);
    BufferedImage pic1 = ImageIO.read(imageInputStream);
    BufferedImage pic2 = pic1.getSubimage(x, y, width, height);
    System.out.println(pic2.getWidth());
    System.out.println(pic2.getHeight());
    File desImage = new File(path1);
    ImageIO.write(pic2, type, desImage);
    byte[] tag = readImageByes(desImage);
    desImage.delete();
    return tag;
  }


  /**
   * 将
   * @param b64
   * @return
   * @throws IOException
   */
  public byte[] picCut(String b64, FaceDetails faceDetails) throws IOException {
    return cutPNG(b64,"src/main/resources/static/",faceDetails.getLeftX(),faceDetails.getLeftY(), faceDetails.getWight(), faceDetails.getHight());
  }
}
