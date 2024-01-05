import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Schedule {

    int day;  // time index

    int workload;  // workload

    int y;  // y = 1 if do compression today. y = 0 otherwise

    int f;  // f = 1 if in valid compression period today. f = 0 otherwise

    int x;  // total today-valid-bandwidth already purchased before

    int x_cloud;  // cloud today-valid-bandwidth already purchased before

    int x_edge;  // edge today-valid-bandwidth already purchased before

    int s;  // total today-needful-bandwidth

    int s_cloud;  // cloud today-needful-bandwidth

    int s_edge;  // edge today-needful-bandwidth

    float currCost;  // cost today


    int sumX() {
        x = x_cloud + x_edge;
        return x;
    }


    /**
     * generate a scheduleList with random workload
     * @param n length
     * @param T valid time cycle
     * @param workload_lb lower bound for random workload
     * @param workload_ub upper bound for random workload
     * @param random control random seed
     * @return
     */
    static List<Schedule> randomScheduleList(int n, int T, int workload_lb, int workload_ub, Random random) {
        List<Schedule> scheduleList = new ArrayList<>();
        int scale = workload_ub - workload_lb;
        for (int i = 0; i < n; i++) {
            scheduleList.add(new Schedule(i, random.nextInt(scale + 1) + workload_lb));
        }
        for (int i = n; i < n + T; i++) {
            scheduleList.add(new Schedule(i, 0));
        }
        return  scheduleList;
    }
    static List<Schedule> randomScheduleList(BasicInf basicInf, Random random) {
        List<Schedule> scheduleList = new ArrayList<>();
        int scale = basicInf.WorkLoad_upperbound - basicInf.WorkLoad_lowerbound;
        for (int i = 0; i < basicInf.n; i++) {
            scheduleList.add(new Schedule(i, random.nextInt(scale + 1) + basicInf.WorkLoad_lowerbound));
        }
        for (int i = basicInf.n; i < basicInf.n + basicInf.T; i++) {
            scheduleList.add(new Schedule(i, 0));
        }
        return  scheduleList;
    }

    /**
     * generate a scheduleList with designated workload
     * @param n
     * @param T
     * @param workloadList
     * @return
     */
    static List<Schedule> designedScheduleList(int n, int T, List<Integer> workloadList) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            scheduleList.add(new Schedule(i, workloadList.get(i)));
        }
        for (int i = n; i < n + T; i++) {
            scheduleList.add(new Schedule(i, 0));
        }
        return scheduleList;
    }

    static void printColumn() {
        String indent = "\t\t";
        String part = ",";
        System.out.println("day" + indent + "WL" + indent + "y" + indent + "f" + indent + part
                + indent + "x" + indent + "x_v" + indent + "x_u" + indent + part +
                indent + "s" + indent + "s_v" + indent + "s_u" + indent + part +
                indent + "currCost");
    }
    void printAll() {
        String indent = "\t\t";
        String part = ",";
        System.out.println(day + indent + workload + indent + y + indent + f + indent + part
                + indent + x + indent + x_cloud + indent + x_edge + indent + part +
                indent + s + indent + s_cloud + indent +s_edge + indent + part +
                indent + currCost);
    }




    public Schedule(int day, int workload) {
        this.day = day;
        this.workload = workload;

        y = 0;
        f = 0;
        x = 0;
        x_cloud = 0;
        x_edge = 0;
        s = 0;
        s_cloud = 0;
        s_edge = 0;
        currCost = 0;
    }

    public Schedule(int day, int workload, int y, int f, int x, int x_cloud, int x_edge, int s, int s_cloud, int s_edge, float currCost) {
        this.day = day;
        this.workload = workload;
        this.y = y;
        this.f = f;
        this.x = x;
        this.x_cloud = x_cloud;
        this.x_edge = x_edge;
        this.s = s;
        this.s_cloud = s_cloud;
        this.s_edge = s_edge;
        this.currCost = currCost;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX_cloud() {
        return x_cloud;
    }

    public void setX_cloud(int x_cloud) {
        this.x_cloud = x_cloud;
    }

    public int getX_edge() {
        return x_edge;
    }

    public void setX_edge(int x_edge) {
        this.x_edge = x_edge;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getS_cloud() {
        return s_cloud;
    }

    public void setS_cloud(int s_cloud) {
        this.s_cloud = s_cloud;
    }

    public int getS_edge() {
        return s_edge;
    }

    public void setS_edge(int s_edge) {
        this.s_edge = s_edge;
    }

    public float getCurrCost() {
        return currCost;
    }

    public void setCurrCost(float currCost) {
        this.currCost = currCost;
    }
}
