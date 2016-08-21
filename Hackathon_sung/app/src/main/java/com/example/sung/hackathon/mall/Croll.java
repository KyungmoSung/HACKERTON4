package com.example.sung.hackathon.mall;




import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Minwoo on 2016. 8. 19..
 */
public class Croll extends Mall{

    public static Map<String, String> cookies;
    private String id = "mw9027";
    private String pw = "jy2411";
    //WebClient webClient;
    //HtmlPage currPage;
    boolean login_success;
    String LoginPage = "http://member.ssg.com/member/popup/popupLogin.ssg";
    String OrderPage = "http://pay.ssg.com/myssg/orderList.ssg?gnb=orderlist";

    public Croll() {


    }
/*
    public ArrayList<String> getData(){

        ArrayList<String> food = new ArrayList<String>();

        try {

            //if(!login_success)
            //    Login();

            currPage = webClient.getPage(OrderPage);
            HtmlTable table = (HtmlTable) currPage.getElementById("table_order_list");

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
        } catch (Exception e) {// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return food;
    }
    /*
    public boolean Login(){

        try{
            webClient = new WebClient(BrowserVersion.FIREFOX_38);
            currPage = webClient.getPage(LoginPage);
            HtmlForm form = (HtmlForm) currPage.getForms().get(0);
            HtmlTextInput inputId = form.getInputByName("mbrLoginId");
            HtmlPasswordInput inputPw = (HtmlPasswordInput) form.getInputByName("password");
            HtmlButton button = (HtmlButton) form.getElementsByTagName("button").get(2);
            inputId.setValueAttribute(id);
            inputPw.setValueAttribute(pw);
            button.click();
            webClient.waitForBackgroundJavaScript(30000);

            login_success = true;

        }catch (Exception e){


            e.printStackTrace();
            login_success = false;
        }
        return login_success;
    }
*/
}
