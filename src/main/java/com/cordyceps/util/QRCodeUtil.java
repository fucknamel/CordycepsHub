package com.cordyceps.util;

import com.cordyceps.common.Const;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    private static Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

    public static String getQRcodePath(String context){
        BitMatrix bitMatrix = new QRCodeUtil().QRencode(context);
        if (bitMatrix == null) {
            return null;
        }

        try {
            BufferedImage image = new BufferedImage(Const.QRcode.WEIGHT, Const.QRcode.HEIGHT, BufferedImage.TYPE_INT_RGB);
            File qrFile = new File("temp" + "." + Const.QRcode.FORMAT);
            ImageIO.write(image, Const.QRcode.FORMAT, qrFile);
            MatrixToImageWriter.writeToPath(bitMatrix, Const.QRcode.FORMAT, qrFile.toPath());

            return qrFile.toPath().toString();
        }catch (Exception e){
            logger.error("生成暂存png文件失败", e);
        }
        return null;
    }

    private BitMatrix QRencode(String context){
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, Const.QRcode.WEIGHT, Const.QRcode.HEIGHT, hints);
            return bitMatrix;
        }catch (Exception e){
            logger.error("生成二维码错误", e);
        }
        return null;
    }
}
