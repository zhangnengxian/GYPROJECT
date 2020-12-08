package cc.dfsoft.uexpress.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import cc.dfsoft.uexpress.common.constant.Constants;

public class QRcodeUtil {
	public static void drawQRCode(String content,String pngName){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String path = Constants.DISK_PATH + Constants.SIGN_DISK_PATH;
		String name = sdf.format(d) + "/" + pngName;
		String realyPathName = path + name+".png";
		drawQRCODE(content,realyPathName);
	}
	public static void drawQRCODE(String content,String filepath){
        try {
            Qrcode qrcode=new Qrcode();

            qrcode.setQrcodeErrorCorrect('Q');
            qrcode.setQrcodeEncodeMode('B');
            qrcode.setQrcodeVersion(15);
            int width= 160;
            int height=160;
            BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2=image.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0,0,160,160);
            g2.setColor(Color.BLACK);
            
            byte[] contentbytes=content.getBytes("utf-8");
            boolean[][] codeout= qrcode.calQrcode(contentbytes);
            for (int i = 0; i <codeout.length; i++) {
                for (int j = 0; j < codeout.length; j++) {

                    if (codeout[j][i]) g2.fillRect(j*2+2,i*2+2,2,2);
                }
            }
            g2.dispose();
            image.flush();
            File imgFile = new File(filepath);
            ImageIO.write(image, "png", imgFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
