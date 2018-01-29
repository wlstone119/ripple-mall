package third.zhimaTest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import com.stone.ripple.util.code.ImageHandler;
import com.stone.ripple.util.code.QRUtil;

public class ImageTest{
    
    public static Object testTryCatch(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try{
            System.out.println("try");
            ImageHandler tt = new ImageHandler();
            BufferedImage dresultImage = tt.loadImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512450287418&di=94c6a972e96831eb3bb65cfd194218a6&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6d81800a19d8bc3ec09c00008b8ba61ea9d34567.jpg");
            ImageIO.write(dresultImage, "png", os);
            return QRUtil.Base64Code(os.toByteArray());
        }catch(Exception e){
            System.out.println("catch");
            return false;
        } finally {
        	System.out.println(returnValue());
        }
    }
    
    public static String returnValue(){
        return "String";
    }
    
    public static void main(String[] args) throws Exception{
    	System.out.println(testTryCatch());
    }
   
}
