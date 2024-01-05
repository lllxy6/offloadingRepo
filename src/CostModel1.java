import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CostModel1 {

    static int n;  // number of time index

    static int T;  // uselife of edge server and compression

    static List<Schedule> scheduleList;  // record schedule situation

    static int totalCost;  // record total cost

    static Random random;  // control random seed

    static Pricing Pricing;  // record pricing

    static float b;  // record discount β

    static int WorkLoad_upperbound;  // the upper bound of workload;

    static float a;  // α

    public static void main(String args[]) {

        // assignment
        scheduleList = new ArrayList<>();
        random = new Random(100);
        Pricing = new Pricing(200, 100, 5000, 1000);
        n = 30;
        totalCost = 0;
        b = Pricing.getDiscount();
        WorkLoad_upperbound = 99;
        T = 5;
        a = (Pricing.getConstCost() / ((1 - b) * Pricing.getPriceBW()));

        // initialize elements of scheduleList.
        // actual length is n + T to convenience of calculation
        for (int i = 0; i < n; i++) {
            scheduleList.add(new Schedule(i, random.nextInt(WorkLoad_upperbound + 1)));
        }
        for (int i = n; i < n + T; i++) {
            scheduleList.add(new Schedule(i, 0));
        }

        // Online Algorithm OA
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
            for (int i = t + 1; i <= dueDay_cloud(t); i++) {
                scheduleList.get(i).x_cloud += schedule.s_cloud;
            }
            for (int i = t + 1; i <= dueDay_edge(t); i++) {
                scheduleList.get(i).x_edge += schedule.s_edge;
            }

            // calculate the cost
            schedule.currCost = Pricing.priceBW * schedule.s_cloud + Pricing.getConstCost() * schedule.y +
                    Pricing.priceBW_edge * schedule.s_edge;

            totalCost += schedule.currCost;
        }

        print();




    }

    static void print() {
        Pricing.print();
        Schedule.printColumn();
        for (int i = 0; i < n; i++) {
            scheduleList.get(i).printAll();
        }
        System.out.println("\ntotal cost = " + totalCost);

    }


    public static int dueDay_cloud(int t) {
        // output the last valid day
        int dueDay_cloud = t + T - 1;
        return dueDay_cloud;
    }

    public static int dueDay_edge(int t) {
        // output the last valid day
        int i;
        int startDay = (int) Math.floor(t / T) * T;
        int endDay = (int) Math.floor(t / T) * T + T - 1;
        for (i = startDay; i < endDay + 1; i++) {
            if (scheduleList.get(i).getY() == 1) {
                break;
            }
        }
        int dueDay_edge = i + T - 1;
        return dueDay_edge;
    }

}
