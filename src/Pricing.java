import java.util.SortedMap;

public class Pricing {

    float priceBW;  // price of cloud bandwidth per unit

    float priceBW_edge;  // price of edge bandwidth per unit

    float priceCompu;  // price of cloud computing resource per unit

    float priceCompu_edge;  // price of edge computing resource per unit

    float costCompression;  // cost of model compression

    float costEdge;  // cost of launch edge server

    float discount;  // bandwidth discount between edge and cloud services

    float discount_Compu;  // computing resource discount between edge and cloud services

    float a;  // α

    public Pricing(float priceBW, float priceBW_edge, float priceCompu, float priceCompu_edge, float costCompression, float costEdge) {
        this.priceBW = priceBW;
        this.priceBW_edge = priceBW_edge;
        this.priceCompu = priceCompu;
        this.priceCompu_edge = priceCompu_edge;
        this.costCompression = costCompression;
        this.costEdge = costEdge;

        this.discount = priceBW_edge / priceBW;
        this.discount_Compu = priceCompu_edge / priceCompu;
        this.a = (this.getConstCost() / ((1 - this.discount) * this.getPriceBW()));
    }

    public Pricing(float priceBW, float priceBW_edge, float costCompression, float costEdge) {
        this.priceBW = priceBW;
        this.priceBW_edge = priceBW_edge;
        this.costCompression = costCompression;
        this.costEdge = costEdge;

        this.discount = priceBW_edge / priceBW;
        this.a = (this.getConstCost() / ((1 - this.discount) * this.getPriceBW()));
    }

//    void printAll() {
//        String indent = "\t";
//        String part = "\n";
//        System.out.println("========Pricing========");
//        System.out.println("priceBW_cloud = " + priceBW + indent + "priceBW_edge = " + priceBW_edge + part +
//                "discount b = " + discount + part +
//                "priceCompu_cloud = " + priceCompu + indent + "priceCompu_edge = " + priceCompu_edge + part +
//                "discount_computing = " + discount_Compu + part +
//                "constantCost = " + getConstCost() + part +
//                "costCompression = " + costCompression + indent + "costEdge = " + costEdge + part
//                );
//    }

    void print() {
        String indent = "    ";
        String part = "\n";
        System.out.println("========Pricing========");
        System.out.println("priceBW_cloud = " + priceBW + indent + "priceBW_edge = " + priceBW_edge + indent +
                "discount b = " + discount + indent +
                "constantCost = " + getConstCost() + indent +
                "costCompression = " + costCompression + indent + "costEdge = " + costEdge
        );
        System.out.printf("a = %.1f\n", a);
    }

    public float getConstCost() {
        return costCompression + costEdge;
    }

    public float getPriceBW() {
        return priceBW;
    }

    public void setPriceBW(float priceBW) {
        this.priceBW = priceBW;
    }

    public float getPriceBW_edge() {
        return priceBW_edge;
    }

    public void setPriceBW_edge(float priceBW_edge) {
        this.priceBW_edge = priceBW_edge;
    }

    public float getPriceCompu() {
        return priceCompu;
    }

    public void setPriceCompu(float priceCompu) {
        this.priceCompu = priceCompu;
    }

    public float getPriceCompu_edge() {
        return priceCompu_edge;
    }

    public void setPriceCompu_edge(float priceCompu_edge) {
        this.priceCompu_edge = priceCompu_edge;
    }

    public float getCostCompression() {
        return costCompression;
    }

    public void setCostCompression(float costCompression) {
        this.costCompression = costCompression;
    }

    public float getCostEdge() {
        return costEdge;
    }

    public void setCostEdge(float costEdge) {
        this.costEdge = costEdge;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
