package cc.dfsoft.uexpress.common.util;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import Decoder.BASE64Decoder;
import cc.dfsoft.uexpress.common.constant.Constants;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 *
 * @author jingjing
 *
 */
public class FileUtil {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private static FileUtil fileUtil = new FileUtil();// 静态锁

	/**
	 *
	 * @param request
	 *            上传文件
	 * @param pathTemp
	 *            部分路径，使用时需加前缀
	 * @param files
	 *            文件集合
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(HttpServletRequest request, String pathTemp, MultipartFile[] files)
			throws IllegalStateException, IOException {
		String path = "";
		String filename = "";
		if (null != files && files.length > 0) {
			// 如果上传方式为1则上传到工程目录下
			if (Constants.UPLOAD_TYPE.equals("1")) {
				path = request.getSession().getServletContext().getRealPath("/");
				path = path.replace("\\", "/");
			} else {
				// 上传到本地磁盘
				path = Constants.DISK_PATH;
			}
			// path为路径前缀如'D:/upload/',pathTemp为路径后缀如'工程编号/年限' 前台传入

			if (null != pathTemp) {
				path = path + "/" + Constants.FIRST_DISK_PATH + "/" + pathTemp + "/";
			}
			// 循环files 支持多文件上传 ，但必须在统一路径下
			for (MultipartFile file : files) {
				// 文件名称没有后缀
				String fileName = files[0].getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				filename = IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS) + "." + fileType;
				// filename = file.getOriginalFilename();
				// 创建存储目录
				File targetFile = new File(path, filename);
				// 如果文件夹路径不存在，则创建路径
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 将上传的文件存储到目标路径中
				file.transferTo(targetFile);
			}
			// 需存库的地址
		}
		return pathTemp + "/" + filename;
	}

	/**
	 *
	 * @param request
	 *            上传文件
	 * @param pathTemp
	 *            部分路径，使用时需加前缀
	 * @param files
	 *            文件集合
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(HttpServletRequest request, String pathTemp, String menuDes, MultipartFile[] files)
			throws Exception  {
		String path = "";
		String filename = "";
		String filenameNew = "";
		if (null != files && files.length > 0) {
			// 如果上传方式为1则上传到工程目录下
			if (Constants.UPLOAD_TYPE.equals("1")) {
				path = request.getSession().getServletContext().getRealPath("/");
				path = path.replace("\\", "/");
			} else {
				// 上传到本地磁盘
				path = Constants.DISK_PATH;
			}
			// path为路径前缀如'D:/upload/',pathTemp为路径后缀如'工程编号/年限' 前台传入

			if (null != pathTemp) {
				path = path + Constants.DIAGRAM_DISK_PATH + pathTemp + "/";
			}

			// 循环files 支持多文件上传 ，但必须在统一路径下
			for (MultipartFile file : files) {
				filename = menuDes + "-" + file.getOriginalFilename();
				String fileName = file.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				filenameNew = IDUtil.getUniqueId("0000") + fileType;
				// 创建存储目录
				File targetFile = new File(path, filename);
				// 如果文件夹路径不存在，则创建路径
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 将上传的文件存储到目标路径中
				file.transferTo(targetFile);
				writeHighQuality(FileUtil.zoomImage(path + filename, 0.8f), path + filenameNew);
				// 删除原文件
				deleteFile(path + filename);

			}
			// 需存库的地址
		}
		return pathTemp + "/" + filenameNew;
	}

	/**
	 * 删除文件
	 *
	 * @param fPath
	 *            文件路劲
	 * @return true 删除成功 false 删除失败
	 */

	public static synchronized boolean deleteFile(String fPath) {
		boolean flag = false;
		SimpleDateFormat sdf=new   SimpleDateFormat( " yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format(new Date());
		// path为路径前缀如'D:/upload/',fPath为路径后缀如'工程编号/年限' 前台传入
		// String path = Constants.DISK_PATH;
		String path = "";
		if (null != fPath) {
			path = path + fPath;
			File file = new File(path);

		// 路径为文件且不为空则进行删除
		if (file != null && file.isFile() && file.exists()) {
			file.delete();
			flag = true;
			logger.info("删除图片路径："+path+"删除时间:"+date);
		}

			// 路径为文件且不为空则进行删除
			if (file != null && file.isFile() && file.exists()) {
				file.delete();
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * 打开文件
	 *
	 * @param fPath
	 * @throws Exception
	 */
	public static boolean openFile(String fPath) throws Exception {
		boolean flag = true;
		if (Desktop.isDesktopSupported()) {
			File file = new File(fPath);
			if (file.isFile() && file.exists()) {
				Desktop desktop = Desktop.getDesktop();
				desktop.open(file);
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static void downLoad(String fileName, String filePath, HttpServletResponse response, boolean isOnLine)
			throws Exception { // 支持在线打开文件的一种方式
		filePath = Constants.DISK_PATH + filePath;
		File f = new File(filePath);
		if (!f.exists()) {
			logger.info("下载文件的路径：" + filePath);
			response.sendError(404, "File not found!");
			return;
		}
		OutputStream out = response.getOutputStream();
		response.reset();
		InputStream br = null;
		try {
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + filePath.replace("#", "%23"));
				URLConnection con = u.openConnection();
				con.connect();
				br = con.getInputStream();
				// response.setContentType(con.getContentType());
				String filename = f.getName();
				String fileType = filename.substring(filename.lastIndexOf("."), filename.length());
				fileName = fileName.replace(",", "，");
				response.setContentType("application/msexcel;charset=UTF-8");

				fileName = StringUtil.isNotBlank(fileName) ? fileName + fileType : f.getName();
				fileName = URLEncoder.encode(fileName, "UTF-8");// 中文乱码：将文件名称转换为ASCII码
				response.addHeader("Content-disposition", "inline;filename=" + fileName);// 文件名称

				// response.setHeader("Content-Disposition",
				// "inline; filename=" + new String(new
				// String(StringUtil.isNotBlank(fileName)?(fileName+fileType).getBytes("gbk"):f.getName().getBytes("gbk"),
				// "iso8859-1")));

				// response.setHeader("Content-Disposition",
				// "inline; filename=" + new String(new
				// String(f.getName().getBytes("gbk"), "iso8859-1")));

				// 写文件
				int b;
				while ((b = br.read()) != -1) {
					// System.out.print("*****b"+b);
					out.write(b);
				}
				out.flush();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// System.out.print("*****e"+e.getMessage());
			logger.error("下载文件报错：" + ExceptionUtil.getMessage(e));
			// e.printStackTrace();
		} finally {
			br.close();
			out.close();
		}
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 *
	 * @author pengtt
	 * @createTime 2016-09-07
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 */
	public static boolean GenerateImage(String imgStr, String imgFilePath) throws Exception {
		// 图像数据为空
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		BufferedOutputStream bufferOut = null;
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				// 调整异常数据
				if (bytes[i] < 0) {
					bytes[i] += 256;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(imgFilePath);
			bufferOut = new BufferedOutputStream(fileOut);
			bufferOut.write(bytes);
			return true;
		} finally {
			bufferOut.flush();
			bufferOut.close();
		}
	}

	/**
	 * 将svg转换成png格式图片
	 *
	 * @param pistrPngFile
	 *            图片路径
	 * @param svgPath
	 *            svg路径
	 */
	@SuppressWarnings("unused")
	public static void changeSvgToPng(String pistrPngFile, String pistrSvgPath) throws Exception {
		Date date = new Date();
		long timeBegin = date.getTime();

		// svg文件路径
		String strSvgURI;
		OutputStream ostream = null;
		// 根据路径获得文件夹
		File fileSvg = new File(pistrSvgPath);
		try {

			// 构造一个表示此抽象路径名的 file:URI
			URI uri = fileSvg.toURI();

			// 根据此 URI 构造一个 URL
			URL url = uri.toURL();

			// 构造此 URL 的字符串表示形式
			strSvgURI = url.toString();

			// 定义一个通用的转码器的输入
			TranscoderInput input = new TranscoderInput(strSvgURI);

			// 定义图片路径
			String strPngPath = pistrPngFile;

			// 输入流
			ostream = new FileOutputStream(strPngPath);

			// 定义单路输出的转码器
			TranscoderOutput output = new TranscoderOutput(ostream);

			// 构造一个新的转码器，产生JPEG图像
			JPEGTranscoder transcoder = new JPEGTranscoder();

			// 设置一个转码过程，JPEGTranscoder.KEY_QUALITY设置输出png的画质精度，0-1之间
			transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));

			// 转换svg文件为png
			transcoder.transcode(input, output);
		} finally {
			ostream.flush();
			ostream.close(); // 关闭输出流
		}
		// 删除svg文件
		fileSvg.delete();
	}

	public static boolean createDir(String destDirName) throws Exception {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			// System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			// System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	public static synchronized String svgToPng(String svgCode1, String pngName1) throws Exception {

		String svgCode = svgCode1;
		String pngName = pngName1;
		String ruls = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String path = Constants.DISK_PATH + Constants.SIGN_DISK_PATH;
		// String path = "D:\\text\\sign";
		FileUtil.createDir(path + sdf.format(d) + "/");
		String name = sdf.format(d) + "/" + pngName;
		String url = path + name + ".png";
		// 文件已存在,重新生成名称
		if (new File(url).exists()) {
			UUID id = UUID.randomUUID();
			String[] idd = id.toString().split("-");
			String i = idd[0] + idd[1] + idd[2];
			name = sdf.format(d) + "/" + i;
		}
		String realyPathName = path + name + ".png";
		String pathName = path + name + "1" + ".png";
		String svgPath = Constants.DISK_PATH + Constants.SIGN_DISK_PATH + "sign.svg";
		// String svgPath = "D:\\sign\\"+"sign.svg";
		// svgcode转.svg文件
		FileUtil.GenerateImage(svgCode, svgPath);
		// .svg文件转png图片
		FileUtil.changeSvgToPng(pathName, svgPath);
		// 等比例缩放
		if (!FileUtil.writeHighQuality(FileUtil.zoomImage(pathName, 0.2f), realyPathName)) {
			FileUtil.deleteFile(pathName);
			FileUtil.deleteFile(realyPathName);
			throw new Exception("图片保存失败!!!");
		}
		// 删除原文件
		FileUtil.deleteFile(pathName);
		return ruls = name + ".png";

	}

	/**
	 * @author pengtt
	 * @createTime 2016-09-12
	 * @param im
	 *            原始图像
	 * @param resizeTimes
	 *            倍数,比如0.5就是缩小一半,0.98等等double类型
	 * @return 返回处理后的图像
	 */
	public static BufferedImage zoomImage(String src, Float resizeTimes) throws Exception {

		BufferedImage result = null;

		File srcfile = new File(src);
		if (!srcfile.exists()) {
			System.out.println("文件不存在");

		}
		BufferedImage im = ImageIO.read(srcfile);

		/* 原始图像的宽度和高度 */
		int width = im.getWidth();
		int height = im.getHeight();

		// 压缩计算
		// resizeTimes = 0.2f; /* 这个参数是要转化成的倍数,如果是1就是转化成1倍 */

		/* 调整后的图片的宽度和高度 */
		int toWidth = (int) (width * resizeTimes) < 1 ? (width * 1) : (int) (width * resizeTimes);
		int toHeight = (int) (height * resizeTimes) < 1 ? (width * 1) : (int) (height * resizeTimes);

		/* 新生成结果图片 */
		result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

		result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0,
				null);
		return result;

	}

	/**
	 * @author pengtt
	 * @createTime 2016-09-12
	 * @param im
	 * @param fileFullPath
	 * @return
	 */
	public static boolean writeHighQuality(BufferedImage im, String fileFullPath) {
		try {
			/* 输出到文件流 */
			FileOutputStream newimage = new FileOutputStream(fileFullPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(im);
			/* 压缩质量 */
			jep.setQuality(1f, true);
			encoder.encode(im, jep);
			/* 近JPEG编码 */
			newimage.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * png文件转换成Jpg
	 *
	 * @param filePath
	 *            png文件路径
	 * @param newFilePath
	 *            jpg路径
	 * @throws IOException
	 */
	public static void pngToJpg(String filePath, String newFilePath) throws IOException {
		BufferedImage bufferedImage;
		// read image file
		bufferedImage = ImageIO.read(new File(filePath));

		// create a blank, RGB, same width and height, and a white background
		BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
		// write to jpeg file
		ImageIO.write(newBufferedImage, "jpg", new File(newFilePath));
		FileUtil.deleteFile(filePath);
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 *
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args)throws Exception  {
		svgToPng("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iMjU3IiBoZWlnaHQ9Ijg1Ij48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDEyIDE1IGwgMjEgMSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTAgNDAgbCAyOSAwIi8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAyMiAyMyBjIDAgMC40MiAtMC4zNyAxNS44NyAwIDI0IGMgMC4zMSA2LjggMiAyMCAyIDIwIi8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxIDcyIGwgNTMgMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gNzkgMjAgYyAtMC4wMiAwLjIxIC0wLjMxIDguNTMgLTEgMTIgYyAtMC4yMSAxLjA0IC0xLjc1IDIgLTIgMyBjIC0wLjM0IDEuMzggMC4zOCAzLjQ5IDAgNSBjIC0wLjg4IDMuNTMgLTQgMTEgLTQgMTEiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDg5IDI1IGwgMSAxNiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTAwIDEgYyAwLjA0IDAuMTYgMS45MyA2IDIgOSBjIDAuNTggMjMuNDQgMCA3MiAwIDcyIi8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxMTMgNSBjIDAuMDUgMC4wNCAxLjk4IDEuOSAzIDIgYyA1LjEgMC40OSAxOCAwIDE4IDAiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDEzNCA4IGMgLTAuMDUgMC4xMiAtMS43OSA0Ljk5IC0zIDcgYyAtMC42NyAxLjEyIC0zIDMgLTMgMyIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTE1IDIyIGMgLTAuMDIgMC4xIC0wLjkzIDMuOTggLTEgNiBjIC0wLjI3IDcuOTUgMCAyNCAwIDI0Ii8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxMjAgMjcgYyAwLjA1IC0wLjA3IDEuODkgLTMuMiAzIC00IGMgMC45MiAtMC42NiAyLjY3IC0wLjkzIDQgLTEgYyA1LjE2IC0wLjI2IDEyLjcgLTEuMjIgMTYgMCBjIDEuNTcgMC41OCAyLjc2IDQuNjkgMyA3IGMgMC42OSA2LjY3IDAgMjIgMCAyMiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTMyIDQyIGMgLTAuMDcgMC4wOSAtMy4xNSAzLjMgLTQgNSBjIC0wLjY4IDEuMzUgLTAuNDQgMy40NCAtMSA1IGMgLTEuMDggMy4wMiAtMi40MiA2LjEgLTQgOSBjIC0yLjQ1IDQuNDkgLTggMTMgLTggMTMiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDEzMiA2MiBsIDEgMSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTMyIDYyIGMgMC4wNyAwLjA1IDIuNTggMi4yMyA0IDMgYyAyLjE2IDEuMTggNyAzIDcgMyIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTQ0IDY4IGwgOCAzIi8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxODQgOCBjIDAuMDcgLTAuMDMgMi42NSAtMS44IDQgLTIgYyA0Ljg0IC0wLjczIDEwLjY4IC0xIDE2IC0xIGMgMy4zNCAwIDguMjMgLTAuMzYgMTAgMSBjIDEuNjcgMS4yOCAyLjcxIDYuMDMgMyA5IGMgMC42NyA2Ljg4IDAgMjIgMCAyMiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTkwIDI3IGMgMC4wNSAtMC4wNCAxLjk4IC0xLjkxIDMgLTIgYyA1LjM4IC0wLjQ5IDE5IDAgMTkgMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTkyIDQyIGwgMTcgMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjAxIDE2IGMgLTAuMDUgMC4zMyAtMS43MiAxMi44OCAtMyAxOSBjIC0wLjM2IDEuNzEgLTEuNzUgMy4zNiAtMiA1IGMgLTAuMzcgMi40MyAwLjMyIDUuNDQgMCA4IGMgLTAuMzMgMi42NCAtMS4wNiA1LjQzIC0yIDggYyAtMS43MyA0Ljc2IC0zLjg3IDEwLjIxIC02IDE0IGMgLTAuNSAwLjkgLTIuMjIgMS4yMiAtMyAyIGwgLTIgMyIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTk5IDYzIGwgMSAxMSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjAyIDYzIGMgMC4wMyAtMC4wNyAxIC0zLjQyIDIgLTQgYyAyLjM0IC0xLjM2IDYuODMgLTIuNjcgMTAgLTMgYyAyLjc1IC0wLjI5IDYuNjQgMC4yMSA5IDEgYyAxLjExIDAuMzcgMi43NyAxLjkxIDMgMyBjIDAuNjEgMi44MiAwLjQ0IDcuOSAwIDExIGMgLTAuMTUgMS4wMiAtMS4xNCAyLjQzIC0yIDMgYyAtMS44MSAxLjIxIC00LjcxIDIuNjIgLTcgMyBjIC0zLjMxIDAuNTUgLTExIDAgLTExIDAiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDIzMSA2IGMgMC4wMyAwLjA5IDEuMTEgMy43NiAyIDUgYyAwLjU5IDAuODMgMyAyIDMgMiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjU0IDggbCAtNiAxMSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjI5IDI3IGwgMjQgMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjMzIDQzIGwgMTkgLTIiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDIzNSA1MiBjIDAuMDcgMC4wMiAyLjY3IDAuOTQgNCAxIGMgNS40OCAwLjI2IDE3IDAgMTcgMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjQyIDMxIGwgMCA1MyIvPjwvc3ZnPg==", "test01");

	}

	public static String svgToPngSign(String svgCode, String pngName) throws Exception {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String path = Constants.DISK_PATH + Constants.SIGN_DISK_PATH;
		FileUtil.createDir(path + sdf.format(d) + "/");
		String name = Constants.SIGN_PCTURE_PATH + sdf.format(d) + "/" + pngName;
		String realyPathName = path + name + ".png";
		String pathName = path + name + "1" + ".png";
		String svgPath = Constants.DISK_PATH + Constants.SIGN_DISK_PATH + Constants.SIGN_PCTURE_PATH + "sign.svg";
		// svgcode转.svg文件
		FileUtil.GenerateImage(svgCode, svgPath);
		// .svg文件转png图片
		FileUtil.changeSvgToPng(pathName, svgPath);
		// 等比例缩放
		FileUtil.writeHighQuality(FileUtil.zoomImage(pathName, 0.2f), realyPathName);
		// 删除原文件
		FileUtil.deleteFile(pathName);
		return name + ".png";

	}

}
