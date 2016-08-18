import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Minwoo on 2016. 8. 18..
 */
public class Main_jsoup {

    public static void main(String args[]){

        String id = "mw9027";
        String pw = "jy2411";

        String LoginPage = "https://member.ssg.com/member/popup/popupLogin.ssg";

        /*
        try {
            URL targetURL = new URL(LoginPage);
            BufferedReader bf = new BufferedReader(new InputStreamReader(targetURL.openStream(), "UTF-8"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("sourceCopy.html"), "UTF-8"));

            HttpConnection.Response rs = (HttpConnection.Response) Jsoup
                    .connect(LoginPage)
                    .data("mode", "login")
                    .data("kinds", "outlogin")
                    .data("MEMBER_ID", id)
                    .data("passwd", pw)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36")
                    .method(Connection.Method.POST).execute();
            Document doc = rs.parse();

            Pattern p = Pattern.compile("alert\\((.+)\\)");
            Matcher m = p.matcher(doc.html());
            m.find();
            //bw.write(m.group());
            //bw.flush();

            Document mainPage = Jsoup.connect(LoginPage)
                    .cookies(rs.cookies()).get();

            bw.write(mainPage.html());
            System.out.println(m.group());
            bw.close();



        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        */
    }

}
