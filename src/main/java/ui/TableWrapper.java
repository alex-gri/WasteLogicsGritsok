package ui;

import bo.Invoice;
import bo.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableWrapper {

    private static final String INVOICE_ROW_CLASS = "gl-0 tgl eo ui-selectee";
    private static final String JOB_ROW_CLASS = "   gl-1 ";
    private static final String JOB_DETAILS_ROW_CLASS = "   gl-2 ";
    private static final String PRICE_ENTITY_ROW_CLASS = "   gl-3 ";
    private static final String CLASS_ATTRIBUTE = "class";

    private By tableId = By.id("MyYardDisclosure9577009378082_frm");
    private By tbodyTag = By.tagName("tbody");

    private String rowXpathPattern = "//tbody[%s]/tr/td";
    private WebElement table;
    private List<WebElement> allRows;
    private Integer numberOfRows;
    private List<Invoice> invoiceList;
    private Invoice invoice;
    private Job job;

    public TableWrapper() {
        table = null;
        allRows = new ArrayList<>();
        numberOfRows = null;
        invoiceList = new ArrayList<>();
        invoice = null;
        job = null;
    }
}
