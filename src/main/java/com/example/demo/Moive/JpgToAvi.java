package com.example.demo.Moive;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;

public class JpgToAvi {
    /**
     * 图片合成视频
     * @param mp4SavePath 视频保存路径
     * @param imageDir 图片地址
     * @param rate 这个可以理解成视频每秒播放图片的数量
     */
    public static boolean jpgToMp4(String mp4SavePath, String imageDir, double rate) {
        FFmpegFrameRecorder recorder = null;
        boolean flag = true;
        try {
            File[] files = new File("pictures").listFiles();
            int [] widthArray = new int[files.length];
            int [] heightArray = new int[files.length];
/**
 * 获取合成视频图片的最大宽高,避免图片比例不一致最终合成效果差
 */
            for (int i = 0; i < files.length; i++) {
                BufferedImage bufferedImage = ImageIO.read(files[i]);
                widthArray[i] = bufferedImage.getWidth();
                heightArray[i] = bufferedImage.getHeight();
            }
/**
 * 这个方法主要是防止图片比例达不到视频合成比例的要求,如果达不到下面条件视频则会无法播放
 * 图片宽：必须要被32整除
 * 图片高：必须要被2整除
 */
            int [] maxWH = getImgMaxWH(widthArray,heightArray);
            recorder = new FFmpegFrameRecorder(mp4SavePath,maxWH[0],maxWH[1]);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
/**
 * 视频质量：目前测试出来的是25-30最清晰,视频质量范围好像是0-40,具体可以自己慢慢测
 */
            recorder.setVideoQuality(25);
            recorder.setFormat("mp4");
            recorder.setFrameRate(rate > 0 ? rate : 1);
            recorder.setPixelFormat(0);
            recorder.start();
            OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
/**
 * 合成视频
 */
            for(int i = 0; i < files.length; i++ ){
                opencv_core.IplImage image = cvLoadImage(files[i].getPath());
                recorder.record(conveter.convert(image));
                opencv_core.cvReleaseImage(image);
            }
            System.out.println("合成成功");
        } catch(Exception e) {
            e.printStackTrace();
            flag = false;
            System.out.println("合成失败");
        } finally {
            try {
                if (recorder != null){
                    recorder.stop();
                    recorder.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private static int[] getImgMaxWH(int[] widthArray, int[] heightArray) {
        int[] ans = new int[]{0,0};
        for(int each:widthArray){
            if(each>ans[0])
                ans[0] = each;
        }
        for(int each:heightArray){
            if(each>ans[1])
                ans[1] = each;
        }
        return ans;
    }
}
