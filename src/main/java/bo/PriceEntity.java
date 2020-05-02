package bo;

import java.util.Objects;

public class PriceEntity {

    private String flatChargeLineTotal;
    private String perTonneLineTotal;
    private String itemLineTotal;

    public void setFlatChargeLineTotal(String flatChargeLineTotal) {
        this.flatChargeLineTotal = flatChargeLineTotal;
    }

    public void setPerTonneLineTotal(String perTonneLineTotal) {
        this.perTonneLineTotal = perTonneLineTotal;
    }

    public void setItemLineTotal(String itemLineTotal) {
        this.itemLineTotal = itemLineTotal;
    }

    @Override
    public String toString() {
        return "PriceEntity{" +
                "flatCharge='" + flatChargeLineTotal + '\'' +
                ", perTonne='" + perTonneLineTotal + '\'' +
                ", item='" + itemLineTotal + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceEntity)) return false;
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(flatChargeLineTotal, that.flatChargeLineTotal) &&
                Objects.equals(perTonneLineTotal, that.perTonneLineTotal) &&
                Objects.equals(itemLineTotal, that.itemLineTotal);
    }
}
