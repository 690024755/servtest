package com.galaxyeye.websocket.tool.selenium; /*
 * Description:com.galaxyeye.websocket.tool.selenium
 * @Date Create on 9:30
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/14日galaxyeye All Rights Reserved.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public final class WebDriverUtils {

    private final static Long time=2000L;

    public static WebElement getElementByXpath(WebDriver webDriver,String xpath){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.xpath(xpath));
        }
    }



    public static WebElement getElementById(WebDriver webDriver,String id){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.id(id));
        }
    }


    public static WebElement getElementByName(WebDriver webDriver,String name){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.name(name));
        }
    }



    public static WebElement getElementByLinkText(WebDriver webDriver,String linkText){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.linkText(linkText));
        }
    }




    public static WebElement getElementByPartialLinkText(WebDriver webDriver,String partialLinkText){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.partialLinkText(partialLinkText));
        }
    }



    public static WebElement getElementByTagName(WebDriver webDriver,String tagName){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.tagName(tagName));
        }
    }



    public static WebElement getElementByClassName(WebDriver webDriver,String className){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.className(className));
        }
    }



    public static WebElement getElementByCssSelector(WebDriver webDriver,String cssSelector){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElement(By.cssSelector(cssSelector));
        }
    }



    public static List<WebElement> getElementsByXpath(WebDriver webDriver, String xpath){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.xpath(xpath));
        }
    }



    public static List<WebElement> getElementsById(WebDriver webDriver,String id){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.id(id));
        }
    }



    public static List<WebElement> getElementsByName(WebDriver webDriver,String name){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.name(name));
        }
    }



    public static List<WebElement> getElementsByLinkText(WebDriver webDriver,String linkText){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.linkText(linkText));
        }
    }



    public static List<WebElement> getElementsByPartialLinkText(WebDriver webDriver,String partialLinkText){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.partialLinkText(partialLinkText));
        }
    }


    public static List<WebElement> getElementsByTagName(WebDriver webDriver,String tagName){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.tagName(tagName));
        }
    }



    public static List<WebElement> getElementsByClassName(WebDriver webDriver,String className){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.className(className));
        }
    }


    public static List<WebElement> getElementsByCssSelector(WebDriver webDriver,String cssSelector){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webDriver.findElements(By.cssSelector(cssSelector));
        }
    }


}
