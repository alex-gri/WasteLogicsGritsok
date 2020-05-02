package bo;

public class InvoiceBuilder {

    private Invoice invoice = new Invoice();

    public InvoiceBuilder setCompany(String company) {
        invoice.setCompany(company);
        return this;
    }

    public InvoiceBuilder setInvoiceAddress(String invoiceAddress) {
        invoice.setInvoiceAddress(invoiceAddress);
        return this;
    }

    public InvoiceBuilder addJob(Job job) {
        invoice.addJob(job);
        return this;
    }

    public Invoice build() {
        return invoice;
    }
}
