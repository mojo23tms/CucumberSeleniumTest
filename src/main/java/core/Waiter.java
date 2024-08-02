package core;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private JavascriptExecutor jsExec;
    private WebDriverWait newWait;

    public Waiter(WebDriver driver) {
       this.jsExec = (JavascriptExecutor) driver;
       this.newWait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    // Waiting method for whole browser page
    public void waitForLoad() {
        waitUntilJSReady();
        waitUntilJQueryReady();
        waitForChildNodes();
    }

    private void waitForJQueryLoad() {
        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) jsExec
                .executeScript("return jQuery.active") == 0);

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if(!jqueryReady) {
            try {
                // Wait for jQuery to load
                newWait.until(jQueryLoad);
            } catch (TimeoutException e) {
                throw new AssertionError("jQuery loading isn't completed after 120 seconds!");
            }
        }
    }

    //Wait for Child Nodes to be present Load
    private void waitForChildNodes() {
        String childNodesReadyScript = "return window.document.hasChildNodes()";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> nodesLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript(childNodesReadyScript).toString());

        //Get Angular is Ready
        boolean angularReady = (Boolean) (jsExec.executeScript(childNodesReadyScript));

        //Wait ANGULAR until it is Ready!
        if(!angularReady) {
            //Wait for Angular to load
            newWait.until(nodesLoad);
        }
    }

    //Wait Until JS Ready
    private void waitUntilJSReady() {
        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> jsExec.executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = (Boolean) jsExec.executeScript("return document.readyState == 'complete'");

        //Wait Javascript until it is Ready!
        if(!jsReady) {
            //Wait for Javascript to load
            newWait.until(jsLoad);
        }
    }

    //Wait Until JQuery and JS Ready
    private void waitUntilJQueryReady() {
        //First check that JQuery is defined on the page. If it is, then wait AJAX
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined == true) {
            //Wait JQuery Load
            waitForJQueryLoad();

            //Wait JS Load
            waitUntilJSReady();
        }
    }

    // Checking element presence on page
    public WebElement forElementPresentBy(By selector) {
        return newWait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public WebElement forElementVisibleBy(By selector) {
        return newWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement forElementVisible(WebElement element) {
        return newWait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement forElementSafelyClicked(By selector) {
        final var element = forElementVisibleBy(selector);
        return forElementSafelyClicked(element);
    }

    public WebElement forElementSafelyClicked(WebElement element) {
        final var elm = newWait.until(ExpectedConditions.elementToBeClickable(element));
        elm.click();
        waitForLoad();
        return elm;
    }

    public WebElement forChildElementSafelyClicked(WebElement root, By selector) {
        return forElementSafelyClicked(root.findElement(selector));
    }
}
