
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minwoo on 2016. 8. 17..
 */
public class Main {

    public static void main(String args[]){

/**
 * Created by Minwoo on 2016. 8. 5..
 */
        /*
        try{
            String id = "mw9027";
            String pw = "jy2411";
            URL targetURL = new URL("http://pay.ssg.com/myssg/orderList.ssg");

            BufferedReader bf;
            String line;

            bf = new BufferedReader(new InputStreamReader(targetURL.openStream(),"UTF-8"));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("sourceCopy.html"),"UTF-8"));
            while((line=bf.readLine())!=null){
                System.out.println(line);
                bw.write(line);
                bw.write("\r\n");
            }
            bw.close();

            String LoginPage = "https://member.ssg.com/member/popup/popupLogin.ssg";     //로그인 폼이 존재하는 메인 페이지
            String mixiTopPage = "http://pay.ssg.com/myssg/orderList.ssg";  //이동할 페이지.
            String encode = "EUC-JP";  //인코딩 방식. 한국의 경우 대부분 UTF-8또는 EUC-KR방식을 사용한다.
            String inputUserName = "email";         //ID FORM
            String inputPassword = "password";   //PW FORM
            String inputNextUrl = "next_url";
            String userName = "account@xxxxx.xxx ";  //ID
            String password = "xxxxxxxx";  //PW
            String nextUrl = "/home.pl";

            CloseableHttpClient client = HttpClients.createDefault();

            DefaultHttpClient httpclient = new DefaultHttpClient();
            //  HttpGet httpget = new HttpGet("http://wwww.xxxx.pe.kr/xxx_x/");
            HttpGet httpget = new HttpGet(LoginPage);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("Login form get: " + response.getStatusLine());
            if (entity != null) {
                entity.consumeContent();
            }
            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = httpclient.getCookieStore().getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
            HttpPost httpost = new HttpPost(LoginPage);

            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("CKWHERE", "0"));
            nvps.add(new BasicNameValuePair("FSID", "0"));
            nvps.add(new BasicNameValuePair("FSID1", ""));
            nvps.add(new BasicNameValuePair("JSESSIONID", "xxxid"));
            nvps.add(new BasicNameValuePair("PCID", ""));
            nvps.add(new BasicNameValuePair("x", "33"));
            nvps.add(new BasicNameValuePair("ffirst", ""));
            nvps.add(new BasicNameValuePair("where", "xxxpass"));

            HttpPost post = new HttpPost("https://nid.naver.com/nidlogin.login");
            post.setHeader("Referer", "http://kin.naver.com/index.nhn");
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");

            List<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("id", id));
            param.add(new BasicNameValuePair("pw", pw));
            //param.add(new BasicNameValuePair("encnm", loginKey));
            param.add(new BasicNameValuePair("enctp", "1"));
            param.add(new BasicNameValuePair("locale", "ko_KR"));
            param.add(new BasicNameValuePair("url", "http://kin.naver.com/index.nhn"));
            param.add(new BasicNameValuePair("smart_LEVEL", "1"));
            post.setEntity(new UrlEncodedFormEntity(param));



            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.ISO_8859_1));
            response = httpclient.execute(httpost);
            entity = response.getEntity();
            System.out.println("Login form get: " + response.getStatusLine());

            System.out.println("Post logon cookies:");
            cookies = httpclient.getCookieStore().getCookies();
            // System.out.println(cookies);
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
            if (entity != null) {
                entity.consumeContent();
            }
            //CloseableHttpClient client = HttpClients.createDefault();


            //targetURL = new URL("http://pay.ssg.com/myssg/orderList.ssg?searchDtType=3&searchStartDt=2016-05-19&searchEndDt=2016-08-17&searchTypeCd=&searchKeyword=&searchDeliveryType=&shppTypeDtlCd=&page=1");

            /*
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
    */
    }

}
