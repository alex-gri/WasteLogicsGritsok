package ui;

import bo.Invoice;
import bo.Job;
import logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static ui.Constants.*;

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

    /*
     * Method parses table into Java objects to work with them further
     */
    public List<Invoice> getInvoices() {

        table = BrowserWrapper.getInstance().findElement(tableId);
        BrowserWrapper.getInstance().moveTo(table);

        // Rows are wrapped by 'tbody' tag
        allRows = table.findElements(tbodyTag);
        numberOfRows = allRows.size();
        Integer i = 0;
        while (i < numberOfRows) {
            while (allRows.get(i).getText().isEmpty()) { // Skip empty rows
                i++;
            }
            parseRow(allRows, i);
            i++;
        }
        return invoiceList;
    }

    private void parseRow(List<WebElement> allRows, Integer i) {
        String rowClass = allRows.get(i).getAttribute(CLASS_ATTRIBUTE);
        List<WebElement> cells = table.findElements(getFormattedRowXpath(i));
        switch (rowClass) {
            case INVOICE_ROW_CLASS:
                invoice = new Invoice();
                invoice.setCompany(cells.get(4).getText());
                invoice.setInvoiceAddress(cells.get(5).getText());
                break;
            case JOB_ROW_CLASS:
                job = new Job();
                job.setOrderID(Integer.valueOf(cells.get(2).getText()));
                break;
            case JOB_DETAILS_ROW_CLASS:
                job.setGrade(cells.get(1).getText());
                job.setWeight(cells.get(2).getText());
                break;
            case PRICE_ENTITY_ROW_CLASS:
                fillPriceEntity(cells);
                verifyNextRow(i);
            break;
            default: Log.error("Error! Unrecognized row class!");
        }
    }

    private void verifyNextRow(Integer i) {
        if ((i < numberOfRows - 1 && allRows.get(i + 1).getAttribute(CLASS_ATTRIBUTE).equals(INVOICE_ROW_CLASS))
                                  || i == numberOfRows - 1) {
            invoice.addJob(job);
            invoiceList.add(invoice);
        } else {
            if (i < numberOfRows - 1 && allRows.get(i + 1).getAttribute(CLASS_ATTRIBUTE).equals(JOB_ROW_CLASS)) {
                invoice.addJob(job);
            }
        }
    }

    private void fillPriceEntity(List<WebElement> cells) {
        String priceEntity = cells.get(1).getText().toLowerCase();
        String priceLineTotal = cells.get(7).getText();
        switch (priceEntity) {
            case FLAT_CHARGE_PRICE_ENTITY:
                job.setFlatChargeLineTotal(priceLineTotal);
                break;
            case PER_TONNE_PRICE_ENTITY:
                job.setPerTonneLineTotal(priceLineTotal);
                break;
            case ITEM_PRICE_ENTITY:
                job.setItemLineTotal(priceLineTotal);
                break;
            default:
                Log.error("Error! Unrecognized or empty price entity!");
        }
    }

    private By getFormattedRowXpath(Integer i) {
        return By.xpath(String.format(rowXpathPattern, i + 1));
    }
}
