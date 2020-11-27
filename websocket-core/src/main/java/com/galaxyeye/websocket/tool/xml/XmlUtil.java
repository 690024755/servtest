package com.galaxyeye.websocket.tool.xml; /*
 * Description:com.galaxyeye.websocket.tool.xml
 * @Date Create on 9:42
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/3/12日galaxyeye All Rights Reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
public class XmlUtil {


    public static Object xmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }


    public static String objectToXmlStr(Object obj,Class<?> load){
        String result = "";
        try{
            JAXBContext context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj,writer);
            result = writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public void Test() throws Exception {
/**
 * xml 字符串解析为对象 example ：
 <xml>
 <appid><![CDATA[wx1dda71a96720abd8]]></appid>
 <bank_type><![CDATA[CFT]]></bank_type>
 <cash_fee><![CDATA[1]]></cash_fee>
 <fee_type><![CDATA[CNY]]></fee_type>
 <is_subscribe><![CDATA[Y]]></is_subscribe>
 <mch_id><![CDATA[1544388501]]></mch_id>
 <nonce_str><![CDATA[tM9E4tcYNozSak1SIEbpd5U0Imx7o4Uu]]></nonce_str>
 <openid><![CDATA[oWfVS1h_kgimhz3PbkQ1E7OAAmO8]]></openid>
 <out_trade_no><![CDATA[2019071710595900000002]]></out_trade_no>
 <result_code><![CDATA[SUCCESS]]></result_code>
 <return_code><![CDATA[SUCCESS]]></return_code>
 <sign><![CDATA[6932D6BA437EA5C47AAEDE1164F21F8720FFF15D1F6BAD350C989587D333F1BD]]></sign>
 <time_end><![CDATA[20190717112358]]></time_end>
 <total_fee>1</total_fee>
 <trade_type><![CDATA[JSAPI]]></trade_type>
 <transaction_id><![CDATA[4200000329201907175186944298]]></transaction_id>
 </xml>
 */
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        Element appidElement =root.addElement("appid");
        appidElement.addCDATA("wx1dda71a96720abd8");
        Element bankTypeElement =root.addElement("bank_type");
        bankTypeElement.addCDATA("CFT");
        Element cashFeeElement =root.addElement("cash_fee");
        cashFeeElement.addCDATA("1");
        Element feeTypeElement =root.addElement("fee_type");
        feeTypeElement.addCDATA("CNY");
        Element isSubscribeElement =root.addElement("is_subscribe");
        isSubscribeElement.addCDATA("1544388501");
        Element mchIdElement =root.addElement("mch_id");
        mchIdElement.addCDATA("CFT");
        Element nonceStrElement =root.addElement("nonce_str");
        nonceStrElement.addCDATA("tM9E4tcYNozSak1SIEbpd5U0Imx7o4Uu");
        Element openidElement =root.addElement("openid");
        openidElement.addCDATA("oWfVS1h_kgimhz3PbkQ1E7OAAmO8");
        Element outTradeNoElement =root.addElement("out_trade_no");
        outTradeNoElement.addCDATA("2019071710595900000002");
        Element resultCodeElement =root.addElement("result_code");
        resultCodeElement.addCDATA("SUCCESS");
        Element returnCodeElement =root.addElement("return_code");
        returnCodeElement.addCDATA("SUCCESS");
        Element signElement =root.addElement("sign");
        signElement.addCDATA("6932D6BA437EA5C47AAEDE1164F21F8720FFF15D1F6BAD350C989587D333F1BD");
        Element timeEndElement =root.addElement("time_end");
        timeEndElement.addCDATA("20190717112358");
        Element totalFeeElement =root.addElement("total_fee");
        totalFeeElement.addText("1");
        Element tradeTypeElement =root.addElement("trade_type");
        tradeTypeElement.addCDATA("JSAPI");
        Element transactionIdElement =root.addElement("transaction_id");
        transactionIdElement.addCDATA("4200000329201907175186944298");
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            FileOutputStream fos = new FileOutputStream("d:/skills.xml");
            XMLWriter writer = new XMLWriter(fos, format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String docXmlText = document.asXML();
        String rootXmlText = root.asXML();

        log.info("docXmlText 请求的参数="+ docXmlText );
        log.info("rootXmlText 请求的参数="+rootXmlText );
        log.info("appidXmlText 返回结果=" +appidElement.asXML());
    }

}
