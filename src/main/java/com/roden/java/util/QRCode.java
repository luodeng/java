package com.roden.java.util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 
 */
public class QRCode {
	private static final String FORMAT = "PNG";

	/**
	 * 生成二维码
	 * 
	 * @param contents
	 *            内容，换行可以用\n
	 * @param dest
	 *            生成二维码图片地址
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @throws WriterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void encode(String contents, String dest, int width,
			int height) throws WriterException, FileNotFoundException,
			IOException {
		contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE,width, height);
		// MatrixToImageWriter.writeToFile(matrix, format, new File(dest));//过时方法不推荐
		MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(dest)));
	}

	/**
	 * 从一张图片解析出二维码信息
	 * 
	 * @param dest
	 *            目标地址
	 * @return String 二维码信息
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ChecksumException
	 * @throws FormatException
	 */
	public static String decode(String dest) throws IOException,
			NotFoundException, ChecksumException, FormatException {
		QRCodeReader reader = new QRCodeReader();
		BufferedImage image = ImageIO.read(new File(dest));
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
		Result result = reader.decode(imageBinaryBitmap);
		// System.out.println("result = "+ result.toString());
		// System.out.println("resultFormat = "+ result.getBarcodeFormat());
		// System.out.println("resultText = "+ result.getText());
		return result.getText();
	}

	public static void main(String[] args) throws WriterException, IOException,
			NotFoundException, ChecksumException, FormatException {
		QRCode.encode("http://www.我爱你中国.org/", "D:\\Target.PNG", 200, 200);		
		System.out.println(QRCode.decode("D:\\Target.PNG"));
		/////////////////////////////////////////////////////////////////////////
		encode2222();
		decode222();
		
	}
	
	public static void encode2222() throws WriterException, IOException{
		 
	      int width = 100;   
	      int height = 100;   
	      String format = "png";   
	      Hashtable hints= new Hashtable();   
	      hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	      //String text = "您好";
	     // BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
	      String text = new String("您好".getBytes("UTF-8"), "ISO-8859-1");  
	      BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);   
	      File outputFile = new File("D:\\Target2.PNG");   
	      MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);   
	}
	
	public static void decode222(){
		try {
            MultiFormatReader formatReader = new MultiFormatReader();			
			BufferedImage image = ImageIO.read(new File("D:\\Target2.PNG"));;
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer  binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//Result result = formatReader.decode(binaryBitmap,hints);
			Result result = formatReader.decode(binaryBitmap);
			System.out.println("result = "+ result.toString());
			//System.out.println("resultFormat = "+ result.getBarcodeFormat());
			//System.out.println("resultText = "+ result.getText());
			            
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}