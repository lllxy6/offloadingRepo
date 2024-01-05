import java.util.List;
import java.util.Random;

public class ChargeModel {

    int n;  // number of time index

    int T;  // uselife of edge server and compression

    int WorkLoad_lowerbound;  // the lower bound of workload

    int WorkLoad_upperbound;  // the upper bound of workload

    Random random;  // control random seed

    Pricing Pricing;  // record pricing

    List<Schedule> scheduleList;  // record schedule situation

    float totalCost;  // record total cost

    float b;  // record discount β

    public ChargeModel(int n, int t, int workLoad_lowerbound, int workLoad_upperbound, Random random, Pricing pricing) {
        this.n = n;
        T = t;
        WorkLoad_lowerbound = workLoad_lowerbound;
        WorkLoad_upperbound = workLoad_upperbound;
        this.random = random;
        Pricing = pricing;
        totalCost = 0;
        b = Pricing.discount;
    }

    public ChargeModel(BasicInf basicInf, Random random) {
        this.n = basicInf.n;
        T = basicInf.T;
        WorkLoad_lowerbound = basicInf.WorkLoad_lowerbound;
        WorkLoad_upperbound = basicInf.WorkLoad_upperbound;
        this.random = random;
        Pricing = basicInf.Pricing;
        totalCost = 0;
        b = Pricing.discount;
    }

    /**
     * deterministic online algorithm 1
     * Compression while (workload >= a AND f = 0)
     */
    void algorithm_D1() {

        float a = Pricing.a;
        for (int t = 0; t < n; t++) {
            Schedule schedule = scheduleList.get(t);
            if (schedule.workload >= a && schedule.f == 0) {
                // Compression and Offloading
                schedule.y = 1;
                // Update the future strategy
                for (int i = 0; i < T; i++) {
                    scheduleList.get(t + i).f = 1;
                }
            }

            // update variables
            schedule.x = schedule.x_cloud + schedule.x_edge;
            schedule.s = Math.max(schedule.workload - schedule.x, 0);
            schedule.s_cloud = schedule.s * (1 - schedule.f);
            schedule.s_edge = schedule.s * schedule.f;

            // update the future schedules
            if (schedule.f == 0) {
                for (int i = t + 1; i <= dueDay_cloud(t); i++) {
                    scheduleList.get(i).x_cloud += schedule.s_cloud;
                }
            } else if (schedule.f == 1) {
                for (int i = t + 1; i <= dueDay_edge(t); i++) {
                    scheduleList. get(i).x_edge += schedule.s_edge;
                }
            }

            // calculate the cost
            schedule.currCost = Pricing.priceBW * schedule.s_cloud + Pricing.getConstCost() * schedule.y +
                    Pricing.priceBW_edge * schedule.s_edge;

            totalCost += schedule.currCost;
        }
    }

    /**
     * print Schedule of this ChargeModel
     */
    void printScheduleResult() {
        Schedule.printColumn();
        for (Schedule schedule : scheduleList) {  // contains the last T redundant schedules
            schedule.printAll();
        }
        System.out.printf("_______ total cost = %.2f ______\n", totalCost);
        System.out.println();
    }

    /**
     * print Schedule of this ChargeModel with index
     * @param index the index of this ChargeModel in the ChargeModelList
     */
    void printScheduleResult(int index) {
        String str = "_______________________________________________";
        System.out.printf("%s ChargeModel %4d %s\n", str, index, str);
        Schedule.printColumn();
        for (Schedule schedule : scheduleList) {
            schedule.printAll();
        }
        System.out.printf("_______ ChargeModel %4d _ total cost = %.2f _______\n", index, totalCost);
        System.out.println();
    }

    /**
     * print Schedule of each ChargeModel
     * @param chargeModels
     */
    static void printScheduleListResult(List<ChargeModel> chargeModels) {
        int times = chargeModels.size();
        for (int i = 0; i < times; i++) {
            ChargeModel cm = chargeModels.get(i);
            cm.printScheduleResult(i);
        }
    }

    /**
     * print totalCost of each ChargeModel
     * @param chargeModels
     */
    static void printChargeModelListCost(List<ChargeModel> chargeModels) {
        int times = chargeModels.size();
        for (int i = 0; i < times; i++) {
            ChargeModel cm = chargeModels.get(i);
            System.out.printf("ChargeModel %4d _ total cost = %.2f\n", i, cm.totalCost);
        }
    }

    /**
     * output the last valid day of cloud resource bought in day t
     * @param t
     * @return
     */
    int dueDay_cloud(int t) {
        // output the last valid day
        int dueDay_cloud = t + T - 1;
        return dueDay_cloud;
    }

    /**
     * output the last valid day of edge resource bought in day t
     * @param t
     * @return
     */
    int dueDay_edge(int t) {
        // output the last valid day
        int i;
        int startDay = Math.max(t - T + 1, 0);
        int endDay = t;
        for (i = endDay; i >= startDay; i--) {
            if (scheduleList.get(i).y == 1) {
                break;
            }
        }
        int dueDay_edge = i + T - 1;
        return dueDay_edge;
    }



}
