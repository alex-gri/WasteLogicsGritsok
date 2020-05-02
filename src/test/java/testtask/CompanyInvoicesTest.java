package testtask;

import bo.*;
import org.testng.annotations.Test;
import ui.BrowserWrapper;
import ui.TableWrapper;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CompanyInvoicesTest extends TestBase {

    @Test
    public void offlinePageInvoiceIsEqualToExpectedOneTest() {
        File offlinePage = new File("src/main/resources/Company invoices - Waste Logics.mhtml");
        BrowserWrapper.getInstance().get("file:///" + offlinePage.getAbsolutePath());
        List<Invoice> allInvoices = new TableWrapper().getInvoices();

        Job expectedJob = new JobBuilder()
                .setOrderID(146566)
                .setGrade("Mixed Municipal Waste")
                .setWeight("0.460 T")
                .setFlatChargeLineTotal("£100.00")
                .setPerTonneLineTotal("£4.60")
                .setItemLineTotal("£110.10")
                .build();
        Invoice expectedInvoice = new InvoiceBuilder()
                .setCompany("TEST CUSTOMER")
                .setInvoiceAddress("TEST ADDRESS, TEST TOWN, 111111")
                .addJob(expectedJob)
                .build();
        Invoice actualInvoice = Invoice.findInvoiceByOrderID(allInvoices, 146566);

        assertThat("Actual invoice is not equal to expected one, or does not contains equal job",
                   actualInvoice, is(equalTo(expectedInvoice)));
    }
}
