import java.util.Random;

public class BasicInf {
    int n;  // number of time index

    int T;  // uselife of edge server and compression

    int WorkLoad_lowerbound;  // the lower bound of workload

    int WorkLoad_upperbound;  // the upper bound of workload

    Random random;  // control random seed

    Pricing Pricing;  // record pricing

    public BasicInf(int n, int t, int workLoad_lowerbound, int workLoad_upperbound, Random random, Pricing pricing) {
        this.n = n;
        T = t;
        WorkLoad_lowerbound = workLoad_lowerbound;
        WorkLoad_upperbound = workLoad_upperbound;
        this.random = random;
        Pricing = pricing;
    }
}
