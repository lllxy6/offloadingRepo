import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Run {

    static Run run = new Run();

    public static void main(String[] args) {

        Random random = new Random(1);

        Pricing Pricing = new Pricing(200, 100, 5000, 1000);

        BasicInf basicInf = new BasicInf(100, 10, 0, 99, random, Pricing);

        int times = 1;

        List<ChargeModel> chargeModels = run.batchInit_Random(times, basicInf);

        chargeModels = run.batchRun_algorithm_D1(chargeModels);

        Pricing.print();

        ChargeModel.printScheduleListResult(chargeModels);
        ChargeModel.printChargeModelListCost(chargeModels);

    }  // end main()

    List<ChargeModel> batchInit_Random(int times, BasicInf basicInf) {

        List<ChargeModel> chargeModels = new ArrayList<>();
        List<Random> randomList = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            Random random = new Random(basicInf.random.nextInt());
            randomList.add(random);

            ChargeModel cm = new ChargeModel(basicInf, random);
            cm.scheduleList = Schedule.randomScheduleList(basicInf, random);
            chargeModels.add(cm);
        }
        return chargeModels;
    }

    List<ChargeModel> batchRun_algorithm_D1(List<ChargeModel> chargeModels) {
        for (ChargeModel cm : chargeModels) {
            cm.algorithm_D1();
        }
        return chargeModels;
    }




}
