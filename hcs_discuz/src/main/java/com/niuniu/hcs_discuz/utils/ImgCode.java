package com.niuniu.hcs_discuz.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 1、生成文本
 * 准备一个字符串，作为验证码的数组
 * 随机从里面抽几个字符，拼到一起作为验证码
 * 2、生成图片
 * 新建一个空白的图片对象，设置长宽和类型
 * 设置随机图片背景
 * 绘制字符
 * 绘制干扰线
 * 3、把文本写到图片里
 * 返回图片
 */

/**
 * 图片验证码
 * @author niuniu
 * @date 2022/2/27
 */
public class ImgCode {
    private int width = 100;           //验证码图片的长和宽
    private int height = 40;
    private int codeCount = 4;          // 验证码字符个数
    private int lineCount = 5;         // 验证码干扰线数
    private String text = "";                //用来保存验证码的文本内容
    private BufferedImage image;            //用来保存验证码图片
    //获取随机数对象
    private Random r = new Random();
    //字体数组
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    //验证码字符串
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    //默认属性创建
    public ImgCode() {
        createImageAndText();
    }

    //自定义属性
    public ImgCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        createImageAndText();
    }

    //随机颜色
    private Color randomColor() {
        int r = this.r.nextInt(225);  //这里为什么是225，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int g = this.r.nextInt(225);
        int b = this.r.nextInt(225);
        return new Color(r, g, b);            //返回一个随机颜色
    }

    //随机字体
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);  //获取随机的字体
        String fontName = fontNames[index];
        int style = r.nextInt(4);         //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int size = r.nextInt(14) + 24;    //随机获取字体的大小
        return new Font(fontName, style, size);   //返回一个随机的字体
    }

    //随机字符
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    //生成验证码和图片
    public void createImageAndText() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //图片绘制对象
        Graphics2D graphics2D = image.createGraphics();

        //填充图片背景
        graphics2D.setColor(randomColor());
        graphics2D.fillRect(0, 0, width, height);

        //生成字符串并填入图片
        for (int i = 0; i < codeCount; i++) {
            String s = randomChar() + "";
            text = text + s;
            float x = i * 1.0F * width / codeCount + r.nextInt(5);   //定义字符的x坐标
            graphics2D.setFont(randomFont());           //设置字体，随机
            graphics2D.setColor(randomColor());         //设置颜色，随机
            graphics2D.drawString(s, x, 30 + r.nextInt(10));
        }

        //划干扰线
        for (int i = 0; i < lineCount; i++) {
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            graphics2D.setColor(randomColor());
            graphics2D.setStroke(new BasicStroke(1.5f));
            graphics2D.drawLine(x1, y1, x2, y2);
        }
    }

//    测试方法
//    public void saveImg() throws Exception {
//        ImageIO.write(image, "JPEG", new File("D://"+text+".jpg"));
//    }

    public Map<String, Object> getImgCode() {
        Map<String, Object> imgCode = new HashMap<>();
        imgCode.put("code", text);
        imgCode.put("image", image);
        return imgCode;
    }

    public String getText() {
        return text;
    }

    public BufferedImage getImage() {
        return image;
    }

}
