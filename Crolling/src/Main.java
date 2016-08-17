import com.sun.net.ssl.HttpsURLConnection;
import sun.net.www.URLConnection;

import java.io.*;
import java.net.URL;

/**
 * Created by Minwoo on 2016. 8. 17..
 */
public class Main {

    public static void main(String args[]){

/**
 * Created by Minwoo on 2016. 8. 5..
 */
        try{
            String id = "mw9027";
            String pw = "jy2411     ";
            String param = "id="+id+"&pw="+pw;
            URL targetURL = new URL("http://www.google.com");

            System.out.println("test");

            //targetURL = new URL("http://pay.ssg.com/myssg/orderList.ssg?searchDtType=3&searchStartDt=2016-05-19&searchEndDt=2016-08-17&searchTypeCd=&searchKeyword=&searchDeliveryType=&shppTypeDtlCd=&page=1");
            URLConnection urlConn = targetURL.openConnection();
            HttpsURLConnection request = (HttpsURLConnection) urlConn;

            System.out.println("test");
            request.setUseCaches(false);
            request.setDoOutput(true);
            request.setDoInput(true);
            request.setFollowRedirects(true);
            request.setInstanceFollowRedirects(true);

            request.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
            request.setRequestProperty("Referer","http...");
            request.setRequestProperty("Cookie","cookie...");
            request.setRequestProperty("Origin","http...");
            request.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            request.setRequestProperty("Content-length",String.valueOf(param.length()));

            request.setRequestMethod("POST");
            OutputStream opstrm = request.getOutputStream();
            opstrm.write(param.getBytes());
            opstrm.flush();
            opstrm.close();

            String buffer = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    request.getInputStream(),"euc-kr"));

            StringBuffer sb = new StringBuffer();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("sourceCopy.html"),"euc-kr"));

            while ((buffer = in.readLine()) != null) {
                sb.append(buffer);
                bw.write(buffer);
                System.out.println(buffer);
                bw.write("\r\n");
            }
            bw.close();
            in.close();



        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
