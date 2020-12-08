package cc.dfsoft.uexpress.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
/**
 * 
 * @author lyq
 * 图片加文字水印
 * 水印文字默认为当前时间：yyyy/MM/dd HH:mm:ss
 * 水印文字位于图片右上角
 *
 */
public class WaterMarkUtil {

	 // 水印透明度
    private static float alpha = 0.5f;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 30);
    // 水印文字颜色
    private static Color color = Color.green;

    /**
     * 
     * @param alpha
     *            水印透明度
     * @param font
     *            水印文字字体
     * @param color
     *            水印文字颜色
     */
    public static void setImageMarkOptions(float alpha, Font font, Color color) {
        if (alpha != 0.0f)
        	WaterMarkUtil.alpha = alpha;
        if (font != null)
        	WaterMarkUtil.font = font;
        if (color != null)
        	WaterMarkUtil.color = color;
    }


    /**
     * 给图片添加水印文字
     * 
     * @param logoText 水印文字  默认获取当前时间 ：yyyy/MM/dd HH:mm:ss
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     */
    public static void markImageByText(String logoText, String srcImgPath,
            String targerPath) {
    	//获取默认水印文字
    	if(logoText==null){
    		logoText=getCurrentTime();
    	}
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
           
            // 4、设置水印文字颜色
            g.setColor(color);
            // 5、设置水印文字Font
            g.setFont(font);
            // 6、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            
            // 7、水印文字位于图片左下角    获取水印文字的长度和宽度
            int [] logoTextInfos = getLogoText(font,logoText);
            //水印文字宽度
            int pwidth=srcImg.getWidth(null)-(logoTextInfos[1]+4);
            //水印文字高度
            int pheight = logoTextInfos[0];
            int tempX = 4;
            int tempY = srcImg.getHeight(null) -(logoTextInfos[0]+4) ;
         // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            String [] texts = logoText.split(",");
            if(texts!=null && texts.length>0){
                for(String s : texts){
                	 // 绘制前一行
                    g.drawString(s, tempX, tempY);
                    //文字长度已经满一行,Y的位置加1字符高度
                    tempY = tempY - pheight;
                }
            }
            //g.drawString(logoText, pwidth, pheight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targerPath);
            ImageIO.write(buffImg, "JPG", os);

            //System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 获取系统当前时间
     * @return 返回格式"yyyy/MM/dd HH:mm:ss"
     */
    public static String getCurrentTime(){
    	String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		return time;
    }
    
    /**
     * 获取水印文字的宽度和高度
     * @param font 水印文字字体
     * @param logoText 水印文字字符串
     * @return 水印文字高度和宽度数组,第一个为水印文字高度 ,第二个为水印文字宽度
     */
    private static int[] getLogoText(Font font,String logoText){
    	int [] logoTextWidthHeight = new int[2];
    	FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);    
        //水印文字高度    
        logoTextWidthHeight[0] = fm.getHeight(); 
        //水印文字宽度
        logoTextWidthHeight[1]=fm.stringWidth(logoText);
    	return logoTextWidthHeight;
    }
    
    /**
     * 示例
     * @param args
     */
    public static void main(String[] args) {
    	 //源图片路径
    	 String srcImgPath = "d:/IMG20190329131039.jpg";
    	 //目标图片路径
         String targerTextPath = "d:/IMG_20180505_172121.jpg";
         
         System.out.println("给图片添加水印文字开始...");
         
         setImageMarkOptions(0.5f,new Font("宋体", Font.BOLD, 75),Color.red);
         // 给图片添加水印文字
        // markImageByText("精度:1234566.111 纬度:1234555551 时间："+getCurrentTime(), srcImgPath, targerTextPath);
         markImageByText("精度:122020.0022 ,纬度:122.09999 ,时间："+getCurrentTime(), srcImgPath, targerTextPath);
         
         System.out.println("给图片添加水印文字结束...");
	}

}
