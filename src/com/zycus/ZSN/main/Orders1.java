// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Orders.java

package com.zycus.ZSN.main;

import common.Functions.CommonFunctions1;
import Orders.POActions;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

// Referenced classes of package Orders:
//            POActions

public class Orders1 extends CommonFunctions1
{

    public Orders1(WebDriver driver, ExtentTest logger, Actions action, String carrier, String shipmentNo, String shipped_via, String service_level, 
            String description, String shipNotice_action)
        throws Exception
    {
        super(driver, logger);
        this.driver = driver;
        this.logger = logger;
        this.action = action;
        this.carrier = carrier;
        this.shipmentNo = shipmentNo;
        this.shipped_via = shipped_via;
        this.service_level = service_level;
        this.shipNotice_action = shipNotice_action;
    }

    public boolean performOrderAction()
        throws Exception
    {
        boolean status = false;
        try
        {
            clearFilter();
            filter_by_checkbox("PO Status", "Confirmed");
            filter_by_checkbox("PO Type", "Standard");
            LogScreenshot("INFO", "sorted on PO Status : Confirmed and PO Type : Standard");
        }
        catch(Exception exception) { }
        POActions1 objPO = new POActions1(driver, action, logger);
        select_actionPO("View", 1);
        objPO.downloadPOasPDF();
        //modified by Anisha as per confirmation with Hinal on 5th July
     //   objPO.previewPOascXML();
        objPO.createShipmentNotice(shipNotice_action, carrier, shipmentNo, shipped_via, service_level);
        status = true;
        return status;
    }

    private WebDriver driver;
    private Actions action;
    private String carrier;
    private String shipNotice_action;
    private String shipmentNo;
    private String shipped_via;
    private String service_level;
    private ExtentTest logger;
}
