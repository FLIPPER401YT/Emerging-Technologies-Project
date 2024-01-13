import java.util.Scanner;

public class KinematicsCalcTester
{
    static Scanner scanner = new Scanner(System.in);
    static boolean calcAgain = true;
    static boolean showAllValues = true;

    public static void main(String[] args)
    {
        while (calcAgain)
        {
            StartCalculation();

            System.out.println("Would you like to calculate again with different values? (Y / N)");
            String answer = scanner.nextLine();

            calcAgain = answer.equalsIgnoreCase("N") ? false : true;
        }
    }

    public static void StartCalculation()
    {
        KinematicsCalculator calc = new KinematicsCalculator();
        double calculation = calc.Calculate();
        boolean showAnotherValue = true;

        while (showAnotherValue)
        {
            if (calc.problemType.equalsIgnoreCase("1-D_Kinematics"))
            {
                if (calc.valueToFind_X.equalsIgnoreCase("delta_X")) System.out.println(calculation + " m");
                else if (calc.valueToFind_X.equalsIgnoreCase("initial_velocity_X")) System.out.println(calculation + " m/s");
                else if (calc.valueToFind_X.equalsIgnoreCase("final_velocity_X")) System.out.println(calculation + " m/s");
                else if (calc.valueToFind_X.equalsIgnoreCase("time")) System.out.println(calculation + " s");
                else System.out.println(calculation + " m/s^2");
            }
            else
            {
                if (calc.valueToFind_XY.equalsIgnoreCase("delta_X")) System.out.println(calculation + " m");
                else if (calc.valueToFind_XY.equalsIgnoreCase("initial_velocity_X")) System.out.println(calculation + " m/s");
                else if (calc.valueToFind_XY.equalsIgnoreCase("delta_Y")) System.out.println(calculation + " m");
                else if (calc.valueToFind_XY.equalsIgnoreCase("initial_velocity_Y")) System.out.println(calculation + " m/s");
                else if (calc.valueToFind_XY.equalsIgnoreCase("final_velocity_Y")) System.out.println(calculation + " m/s");
                else if (calc.valueToFind_XY.equalsIgnoreCase("time")) System.out.println(calculation + " s");
                else System.out.println(calculation + " m/s^2");
            }

            showAnotherValue = false;

            System.out.println("Would you like to find out more values from the same calculation? (Y / N)");
            String answer = scanner.nextLine();

            showAnotherValue = answer.equalsIgnoreCase("y") ? true : false;

            if (showAnotherValue)
            {
                calc.getValueToFind(calc.problemType.equalsIgnoreCase("1-D_Kinematics"));
                calculation = calc.changeCalcValue();
            }
        }

        showAllValues = true;
        while(showAllValues)
        {
            System.out.println("Would you like to see all the values from the equation? (Y / N)");
            String answer = scanner.nextLine();

            showAllValues = answer.equalsIgnoreCase("Y") ? true : false;
            if (showAllValues) System.out.println(calc.toString());
        }
    }
}
