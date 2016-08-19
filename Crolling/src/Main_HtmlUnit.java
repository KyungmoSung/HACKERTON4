import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minwoo on 2016. 8. 18..
 */
public class Main_HtmlUnit {


    public static Map<String, String> cookies;
    public static void main(String arvs[]){

        WebClient webClient;
        HtmlPage currPage;
        String LoginPage = "http://member.ssg.com/member/popup/popupLogin.ssg";
        String OrderPage = "http://pay.ssg.com/myssg/orderList.ssg?gnb=orderlist";
        //LoginPage = "http://www.facebook.com";
        String id = "mw9027";
        String pw = "jy2411";


        try {
            webClient = new WebClient(BrowserVersion.FIREFOX_38);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("sourceCopy2.html"), "UTF-8"));

            currPage = webClient.getPage(LoginPage);
            HtmlForm form = (HtmlForm) currPage.getForms().get(0);
            HtmlTextInput inputId = form.getInputByName("mbrLoginId");
            HtmlPasswordInput inputPw = (HtmlPasswordInput) form.getInputByName("password");
            HtmlButton button = (HtmlButton) form.getElementsByTagName("button").get(2);
            inputId.setValueAttribute(id);
            inputPw.setValueAttribute(pw);

            button.click();
            webClient.waitForBackgroundJavaScript(30000);

            currPage = webClient.getPage(OrderPage);
            HtmlTable table = (HtmlTable) currPage.getElementById("table_order_list");


            ArrayList<String> food = new ArrayList<String>();

            for (HtmlTableRow span : table.getBodies().get(0).getRows()) {

                HtmlAnchor anchor = (HtmlAnchor) span.getElementsByTagName("a").get(0);
                HtmlTable table2 ;
                currPage = anchor.click();
                webClient.waitForBackgroundJavaScript(30000);
                table2 = (HtmlTable) currPage.getElementById("eachCart0");
                for (HtmlTableRow span2 : table2.getBodies().get(0).getRows()) {
                    food.add(span2.getElementsByTagName("a").get(0).getTextContent());
                }


            }
            for(String s :food){

                System.out.println(s);
            }
            bw.write(currPage.toString());
            bw.close();
            /*
            cookies = new HashMap<String, String>();
            CookieManager cookieManager = webClient.getCookieManager();
            java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
            for (Cookie c : cookieSet) {
                cookies.put(c.getName(), c.getValue());
                System.out.println(c.getName() + " " + c.getValue());
            }
            Document doc = null;
            doc = Jsoup.connect(OrderPage).cookies(cookies).get();

            //Element table = doc.select("table").first();
            //Element tbody = table.select("tbody").first();

            //HtmlTable table = (HtmlTable)currPage.getElementById("table_order_list");
            //bw.write(tbody.html());
            bw.close();

            //for(Element span : tbody.getElementsByClass("adv_num2")){

            //    System.out.println(span.select("a").first().text());
        //}
        */
        } catch (Exception e) {// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
