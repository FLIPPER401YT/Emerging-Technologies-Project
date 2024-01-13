import java.util.ArrayList;
import java.util.Scanner;
//import java.util.*;

public class KinematicsCalculator
{
    private int problemTypeID;
    
    boolean error_1 = false;
    boolean error_2 = false;
    Scanner scanner = new Scanner(System.in);
    String problemType;
    String[] values = new String[7];
    String dX = "delta_X";
    String vXI = "initial_velocity_X";
    String vXF = "final_velocity_X";
    String t = "time";
    String acc = "acceleration";
    String dY = "delta_Y";
    String vYI = "initial_velocity_Y";
    String vYF = "final_velocity_Y";
    ArrayList<Double> calculatedValues = new ArrayList<Double>();
    
    public String valueToFind_X = "";
    public String valueToFind_XY = "";

    public KinematicsCalculator()
    {
        for (int i = 0; i < values.length; i++) values[i] = "empty";
        getInfo();
    }
    
    private void getInfo()
    {
        System.out.println("What type of problem are you trying to solve? ('1-D_Kinematics' or '2-D_Kinematics')");
        problemType = scanner.nextLine();
        
        while (!problemType.equalsIgnoreCase("1-D_Kinematics") && !problemType.equalsIgnoreCase("2-D_Kinematics"))
        {
            System.out.println("Please enter a valid response (1-D_Kinematics or 2-D_Kinematics)");
            problemType = scanner.nextLine();
        }
            
        if (problemType.equalsIgnoreCase("1-D_Kinematics") || problemType.equalsIgnoreCase("1-D-Kinematics"))
        {
            EnterValues(true);
            getValueToFind(true);
        }
        else if (problemType.equalsIgnoreCase("2-D_Kinematics") || problemType.equalsIgnoreCase("2-D-Kinematics"))
        {
            EnterValues(false);
            getValueToFind(false);
        }
    }

    private void EnterValues(boolean type)
    {
        if (type)
        {
            System.out.println("Enter your value for X displacement in meters (enter N/A if you don't have it)");
            values[0] = scanner.nextLine();

            System.out.println("Enter your value for initial velocity X in m/s (enter N/A if you don't have it)");
            values[1] = scanner.nextLine();

            System.out.println("Enter your value for final velocity X in m/s (enter N/A if you don't have it)");
            values[2] = scanner.nextLine();

            System.out.println("Enter your value for time in seconds (enter N/A if you don't have it)");
            values[3] = scanner.nextLine();

            System.out.println("Enter your value for acceleration in m/s^2 (enter N/A if you don't have it)");
            values[4] = scanner.nextLine();

            int counter = 0;
            for (int i = 0; i < 4; i++)
                if (values[i].equalsIgnoreCase("N/A")) counter++;
            
            if (counter > 2)
            {
                System.out.println("You need at least three of the five values to do this calculation please try again.");
                EnterValues(type);
            }
        }
        else
        {
            System.out.println("Enter your value for X displacement in meters (enter N/A if you don't have it)");
            values[0] = scanner.nextLine();

            System.out.println("Enter your value for initial velocity X in m/s (enter N/A if you don't have it)");
            values[1] = scanner.nextLine();

            System.out.println("Enter your value for Y displacement in meters (enter N/A if you don't have it)");
            values[2] = scanner.nextLine();

            System.out.println("Enter your value for initial velocity Y in m/s (enter N/A if you don't have it)");
            values[3] = scanner.nextLine();

            System.out.println("Enter your value for final velocity Y in m/s (enter N/A if you don't have it)");
            values[4] = scanner.nextLine();

            System.out.println("Enter your value for time in seconds (enter N/A if you don't have it)");
            values[5] = scanner.nextLine();

            int counter = 0;
            for (int i = 0; i < 6; i++)
                if (values[i].equalsIgnoreCase("N/A")) counter++;
            
            if (counter > 4)
            {
                System.out.println("You need at least three of the seven values to do this calculation please try again.");
                EnterValues(type);
            }
        }
    }

    public double Calculate()
    {
        Double result = 0.0;
        error_1 = false;
        error_2 = false;

        calculatedValues.clear();

        if (problemTypeID == 1)
        {
            int equationID = GetEquationID(problemTypeID);

            if (equationID < 5)
            {
                if (equationID == 0)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    
                    Double calculatedAcceleration = ((Math.pow(calculatedValues.get(2), 2) - Math.pow(calculatedValues.get(1), 2)) / calculatedValues.get(0)) / 2.0;
                    calculatedValues.add(calculatedAcceleration);
                    
                    Double calculatedTime = (calculatedValues.get(2) - calculatedValues.get(1)) / calculatedValues.get(4);
                    calculatedValues.set(3, calculatedTime);
                }
                else if (equationID == 1)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));

                    Double calculatedFinalVelocity = ((calculatedValues.get(0) * 2) / calculatedValues.get(3)) - calculatedValues.get(1);
                    calculatedValues.set(2, calculatedFinalVelocity);

                    Double calculatedAcceleration = ((Math.pow(calculatedValues.get(2), 2) - Math.pow(calculatedValues.get(1), 2)) / calculatedValues.get(0)) / 2.0;
                    calculatedValues.add(calculatedAcceleration);
                }
                else if (equationID == 2)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double changeNegative = (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)) > 0 ? 1.0 : -1.0;
                    Double calculatedFinalVelocity = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)));
                    calculatedValues.set(2, calculatedFinalVelocity);

                    Double calculatedTime = (calculatedValues.get(0) * 2) / (calculatedValues.get(1) + calculatedValues.get(2));
                    calculatedValues.set(3, calculatedTime);
                }
                else if (equationID == 3)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));

                    Double calculatedInitialVelocity = ((calculatedValues.get(0) * 2) / calculatedValues.get(3)) - calculatedValues.get(2);
                    calculatedValues.set(1, calculatedInitialVelocity);

                    Double calculatedAcceleration = ((Math.pow(calculatedValues.get(2), 2) - Math.pow(calculatedValues.get(1), 2)) / calculatedValues.get(0)) / 2.0;
                    calculatedValues.add(calculatedAcceleration);
                }
                else
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double changeNegative = (Math.pow(calculatedValues.get(2), 2) - ((2 * calculatedValues.get(4)) * calculatedValues.get(0))) > 0 ? 1.0 : -1.0;
                    Double calculatedInitialVelocity = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(2), 2) - ((2 * calculatedValues.get(4)) * calculatedValues.get(0))));
                    calculatedValues.set(1, calculatedInitialVelocity);

                    Double calculatedTime = (calculatedValues.get(0) * 2) / (calculatedValues.get(1) + calculatedValues.get(2));
                    calculatedValues.set(3, calculatedTime);
                }
            }
            else
            {
                if (equationID == 5)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double calculateInitialVelocity = ((calculatedValues.get(0) * -1) + ((0.5 * calculatedValues.get(4)) * Math.pow(calculatedValues.get(3), 2))) / (calculatedValues.get(3) * -1);
                    calculatedValues.set(1, calculateInitialVelocity);

                    Double changeNegative = (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)) > 0 ? 1.0 : -1.0;
                    Double calculatedFinalVelocity = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)));
                    calculatedValues.set(2, calculatedFinalVelocity);
                }
                else if (equationID == 6)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);

                    Double calculatedXDisplacement = 0.5 * (calculatedValues.get(1) + calculatedValues.get(2)) * calculatedValues.get(3);
                    calculatedValues.set(0, calculatedXDisplacement);

                    Double calculatedAcceleration = ((Math.pow(calculatedValues.get(2), 2) - Math.pow(calculatedValues.get(1), 2)) / calculatedValues.get(0)) / 2.0;
                    calculatedValues.add(calculatedAcceleration);
                }
                else if (equationID == 7)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double calculateXDisplacement = (Math.pow(calculatedValues.get(2), 2) - Math.pow(calculatedValues.get(1), 2)) / (2 * calculatedValues.get(4));
                    calculatedValues.set(0, calculateXDisplacement);

                    Double calculatedTime = (calculatedValues.get(0) * 2) / (calculatedValues.get(1) + calculatedValues.get(2));
                    calculatedValues.set(3, calculatedTime);
                }
                else if (equationID == 8)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double calculatedXDisplacement = (calculatedValues.get(1) * calculatedValues.get(3)) + ((0.5 * calculatedValues.get(4)) * Math.pow(calculatedValues.get(3), 2));
                    calculatedValues.set(0, calculatedXDisplacement);

                    Double changeNegative = (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)) > 0 ? 1.0 : -1.0;
                    Double calculatedFinalVelocity = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(1), 2) + (2 * calculatedValues.get(4)) * calculatedValues.get(0)));
                    calculatedValues.set(2, calculatedFinalVelocity);
                }
                else
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));

                    Double calculatedInitialVelocity = -1.0 * ((-1.0 * calculatedValues.get(2)) + (calculatedValues.get(4) * calculatedValues.get(3)));
                    calculatedValues.set(1, calculatedInitialVelocity);

                    Double calculatedXDisplacement = (calculatedValues.get(1) * calculatedValues.get(3)) + ((0.5 * calculatedValues.get(4)) * Math.pow(calculatedValues.get(3), 2));
                    calculatedValues.set(0, calculatedXDisplacement);
                }
            }

            result = changeCalcValue();
        }
        else
        {
            int equationID = GetEquationID(problemTypeID);

            if (equationID < 6)
            {
                if (equationID == 0)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);

                    Double calculatedTime = calculatedValues.get(0) / calculatedValues.get(1);
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedInitialVelocityY = ((-1.0 * calculatedValues.get(2)) + ((0.5 * -9.8) * Math.pow(calculatedTime, 2))) / calculatedTime;
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedFinalVelocityY = ((calculatedValues.get(0) * 2) / calculatedTime) - calculatedInitialVelocityY;
                    calculatedValues.set(4, calculatedFinalVelocityY);
                }
                else if (equationID == 1)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);

                    Double calculatedTime = calculatedValues.get(0) / calculatedValues.get(1);
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedTime);
                    calculatedValues.set(4, calculatedFinalVelocityY);

                    Double calculatedYDisplacement = 0.5 * (calculatedValues.get(3) + calculatedFinalVelocityY) * calculatedTime;
                    calculatedValues.set(2, calculatedYDisplacement);
                }
                else if (equationID == 2)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double calculatedTime = calculatedValues.get(0) / calculatedValues.get(1);
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedInitialVelocityY = -1.0 * ((-1.0 * calculatedValues.get(4)) + (-9.8 * calculatedTime));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedYDisplacement = 0.5 * (calculatedValues.get(3) + calculatedValues.get(4)) * calculatedTime;
                    calculatedValues.set(2, calculatedYDisplacement);
                }
                else if (equationID == 3)
                {
                    //UNABLE TO COMPLETE EQUATION ONLY HAS ACCELERATION AND TIME
                    error_1 = true;
                }
                else
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);

                    Double c = Math.pow(calculatedValues.get(3), 2) - (4 * (-4.9 * (-1.0 * calculatedValues.get(2))));
                    Double changeNegativeC = c > 0 ? 1.0 : -1.0;
                    Double d = changeNegativeC * Math.sqrt(changeNegativeC * c);
                    Double calculatedTime;
                    if (d > 0)
                    {
                        Double a = ((-1.0 * calculatedValues.get(3)) + d) / (2 * -4.9);
                        Double b = ((-1.0 * calculatedValues.get(3)) - d) / (2 * -4.9);

                        calculatedTime = a > b ? a : b;
                    }
                    else if (d == 0) calculatedTime = (-1.0 * calculatedValues.get(3)) / (2 * -4.9); // CONTINUE HERE WITH MAKING THE QUADRATIC EQUATION
                    else if ((((-1.0 * calculatedValues.get(3)) / (2 * -9.8)) + (changeNegativeC * (Math.sqrt(changeNegativeC * c)))) > (((-1.0 * calculatedValues.get(3)) / (2 * -9.8)) - (changeNegativeC * (Math.sqrt(changeNegativeC * c)))))
                    {
                        calculatedTime = (((-1.0 * calculatedValues.get(3)) / (2 * -9.8)) + (changeNegativeC * (Math.sqrt(changeNegativeC * c))));
                    }
                    else calculatedTime = (((-1.0 * calculatedValues.get(3)) / (2 * -9.8)) - (changeNegativeC * (Math.sqrt(changeNegativeC * c))));
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedTime;
                    calculatedValues.set(1, calculatedInitialVelocityX);

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedTime);
                    calculatedValues.set(4, calculatedFinalVelocityY);
                }
            }
            else if (equationID < 13)
            {
                if (equationID == 6)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double changeNegative = ((Math.pow(calculatedValues.get(4), 2)) + ((2 * 9.8) * calculatedValues.get(2))) > 0 ? 1.0 : -1.0;
                    Double calculatedInitialVelocityY = changeNegative * Math.sqrt(changeNegative * ((Math.pow(calculatedValues.get(4), 2)) + ((2 * 9.8) * calculatedValues.get(2))));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedTime = (calculatedValues.get(2) * 2) / (calculatedInitialVelocityY + calculatedValues.get(4));
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedTime;
                    calculatedValues.set(1, calculatedInitialVelocityX);
                }
                else if (equationID == 7)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedInitialVelocityY = -1.0 * (((-1.0 * calculatedValues.get(2)) + (-4.9 * Math.pow(calculatedValues.get(5), 2))) / calculatedValues.get(5));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedValues.get(5);
                    calculatedValues.set(1, calculatedInitialVelocityX);

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedValues.get(5));
                    calculatedValues.set(4, calculatedFinalVelocityY);
                }
                else if (equationID == 9)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double calculatedTime = (calculatedValues.get(4) - calculatedValues.get(3)) / -9.8;
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedTime;
                    calculatedValues.set(1, calculatedInitialVelocityX);

                    Double calculatedYDisplacement = 0.5 * (calculatedValues.get(3) + calculatedValues.get(4)) * calculatedTime;
                    calculatedValues.set(2, calculatedYDisplacement);
                }
                else if (equationID == 10)
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedYDisplacement = (calculatedValues.get(3) * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedValues.get(5);
                    calculatedValues.set(1, calculatedInitialVelocityX);

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedValues.get(5));
                    calculatedValues.set(4, calculatedFinalVelocityY);
                }
                else
                {
                    calculatedValues.add(Double.valueOf(values[0]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedInitialVelocityX = calculatedValues.get(0) / calculatedValues.get(5);
                    calculatedValues.set(1, calculatedInitialVelocityX);

                    Double calculatedInitialVelocityY = -1.0 * ((-1.0 * calculatedValues.get(4)) + (-9.8 * calculatedValues.get(5)));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedYDisplacement = (calculatedInitialVelocityY * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);
                }
            }
            else if (equationID < 21)
            {
                if (equationID == 15)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);

                    Double changeNegative = (Math.pow(calculatedValues.get(3), 2) + (2 * -9.8 * calculatedValues.get(2))) > 0 ? 1.0 : -1.0;
                    Double calculatedFinalVelocityY = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(3), 2) + (2 * -9.8 * calculatedValues.get(2))));
                    calculatedValues.set(4, calculatedFinalVelocityY);

                    Double calculatedTime = (calculatedValues.get(4) - calculatedValues.get(3)) / -9.8;
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedTime;
                    calculatedValues.set(0, calculatedXDisplacement);
                }
                else if (equationID == 16)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double changeNegative = ((Math.pow(calculatedValues.get(4), 2)) + (2 * 9.8 * calculatedValues.get(2))) > 0 ? 1.0 : -1.0;
                    Double calculatedInitialVelocityY = changeNegative * Math.sqrt(changeNegative * ((Math.pow(calculatedValues.get(4), 2)) + (2 * 9.8 * calculatedValues.get(2))));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedTime = (calculatedValues.get(4) - calculatedValues.get(3)) / -9.8;
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedTime;
                    calculatedValues.set(0, calculatedXDisplacement);
                }
                else if (equationID == 17)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[5]));
                    
                    Double calculatedInitialVelocityY = -1.0 * (((-1.0 * calculatedValues.get(2)) + (-4.9 * Math.pow(calculatedValues.get(5), 2))) / calculatedValues.get(5));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double changeNegative = (Math.pow(calculatedValues.get(3), 2) + (2 * -9.8 * calculatedValues.get(2))) > 0 ? 1.0 : -1.0;
                    Double calculatedFinalVelocityY = changeNegative * Math.sqrt(changeNegative * (Math.pow(calculatedValues.get(3), 2) + (2 * -9.8 * calculatedValues.get(2))));
                    calculatedValues.set(4, calculatedFinalVelocityY);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedValues.get(5);
                    calculatedValues.set(0, calculatedXDisplacement);
                }
                else if (equationID == 19)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double calculatedTime = (calculatedValues.get(4) - calculatedValues.get(3)) / -9.8;
                    calculatedValues.set(5, calculatedTime);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedTime;
                    calculatedValues.set(0, calculatedXDisplacement);

                    Double calculatedYDisplacement = (calculatedValues.get(3) * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);
                }
                else
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedValues.get(5));
                    calculatedValues.set(4, calculatedFinalVelocityY);

                    Double calculatedYDisplacement = (calculatedValues.get(3) * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedValues.get(5);
                    calculatedValues.set(0, calculatedXDisplacement);
                }
            }
            else
            {
                if (equationID == 22)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[1]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedInitialVelocityY = -1.0 * ((-1.0 * calculatedValues.get(4)) + (-9.8 * calculatedValues.get(5)));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    Double calculatedYDisplacement = (calculatedValues.get(3) * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);

                    Double calculatedXDisplacement = calculatedValues.get(1) * calculatedValues.get(5);
                    calculatedValues.set(0, calculatedXDisplacement);
                }
                else if (equationID == 25)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(-1.0);

                    Double calculatedTime = (calculatedValues.get(4) - calculatedValues.get(3)) / -9.8;
                    calculatedValues.set(5, calculatedTime);

                    // CANNOT SOLVE FOR THE X DISPLACEMENT OR THE X VELOCITY
                    error_2 = true;
                }
                else if (equationID == 26)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedFinalVelocityY = calculatedValues.get(3) + (-9.8 * calculatedValues.get(5));
                    calculatedValues.set(4, calculatedFinalVelocityY);

                    // CANNOT SOLVE FOR THE X DISPLACEMENT OR THE X VELOCITY
                    error_2 = true;
                }
                else if (equationID == 28)
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[2]));
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedInitialVelocityY = -1.0 * ((-1.0 * calculatedValues.get(4)) + (-9.8 * calculatedValues.get(5)));
                    calculatedValues.set(3, calculatedInitialVelocityY);

                    // CANNOT SOLVE FOR THE X DISPLACEMENT OR THE X VELOCITY
                    error_2 = true;
                }
                else
                {
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(-1.0);
                    calculatedValues.add(Double.valueOf(values[3]));
                    calculatedValues.add(Double.valueOf(values[4]));
                    calculatedValues.add(Double.valueOf(values[5]));

                    Double calculatedYDisplacement = (calculatedValues.get(3) * calculatedValues.get(5)) + (-4.9 * Math.pow(calculatedValues.get(5), 2));
                    calculatedValues.set(2, calculatedYDisplacement);

                    // CANNOT SOLVE FOR THE X DISPLACEMENT OR THE X VELOCITY
                    error_2 = true;
                }
            }

            if (error_1)
            {
                System.out.println("Unable to solve problem. Not enough information given. Please try again and input more or different values");
                EnterValues(problemTypeID == 1);
                getValueToFind(problemTypeID == 1);
                result = this.Calculate();
            }
            else if (error_2 && (valueToFind_XY.equalsIgnoreCase("delta_X") || valueToFind_XY.equalsIgnoreCase("initial_velocity_X")))
            {
                System.out.println("Unable to find the X Displacement or X Velocity. Not enought information given. Please input an X Displacement or X Velocity to solve problem.");
                EnterValues(problemTypeID == 1);
                getValueToFind(problemTypeID == 1);
                result = this.Calculate();
            }
            else result = changeCalcValue();
        }

        return result;
    }

    private int GetEquationID(int pID)
    {
        if (pID == 1)
        {
            int equation = 0;
            for (int i = 0; i < 5; i++)
            {
                for (int j = i + 1; j < 5; j++)
                {
                    for (int k = j + 1; k < 5; k++)
                    {
                        if (!values[i].equalsIgnoreCase("N/A") && !values[j].equalsIgnoreCase("N/A") && !values[k].equalsIgnoreCase("N/A")) return equation;
                        equation++;
                    }
                }
            }
        }
        else
        {
            int equation = 0;
            for (int i = 0; i < 7; i++)
            {
                for (int j = i + 1; j < 7; j++)
                {
                    for (int k = j + 1; k < 7; k++)
                    {
                        if (!values[i].equalsIgnoreCase("N/A") && !values[j].equalsIgnoreCase("N/A") && !values[k].equalsIgnoreCase("N/A")) return equation;
                        equation++;
                    }
                }
            }
        }
        return -1;
    }

    public String toString()
    {
        if (problemTypeID == 1)
            return ("X Displacement: " + calculatedValues.get(0) + "m Initial Velocity X: " + calculatedValues.get(1) + "m/s Final Velocity X: " + calculatedValues.get(2) + "m/s Time: " + calculatedValues.get(3) + "s Acceleration: " + calculatedValues.get(4) + "m/s^2");
        else if (!error_1 && !error_2)
            return ("X Displacement: " + calculatedValues.get(0) + "m Initial Velocity X: " + calculatedValues.get(1) + "m/s Y Displacement: " + calculatedValues.get(2) + "m Initial Velocity Y: " + calculatedValues.get(3) + "m/s Final Velocity Y: " + calculatedValues.get(4) + "m/s Time: " + calculatedValues.get(5) + "s Acceleration: -9.8m/s^2");

        return ("Y Displacement: " + calculatedValues.get(2) + "m Initial Velocity Y: " + calculatedValues.get(3) + " m/s Final Velocity Y: " + calculatedValues.get(4) + "m/s Time: " + calculatedValues.get(5) + "s Acceleration: -9.8m/s^2");
    }

    public void getValueToFind(boolean currentProblemType)
    {
        if (currentProblemType)
        {
            problemTypeID = 1;
            System.out.println("What value are you trying to find? (delta_X, initial_velocity_X, final_velocity_X, time, or acceleration)");
            valueToFind_X = scanner.nextLine();

            while (!valueToFind_X.equalsIgnoreCase(dX) && !valueToFind_X.equalsIgnoreCase(vXI) && !valueToFind_X.equalsIgnoreCase(vXF) && !valueToFind_X.equalsIgnoreCase(t) && !valueToFind_X.equalsIgnoreCase(acc))
            {
                System.out.println("Please enter a valid response (delta_X, initial_velocity_X, final_velocity_X, time, acceleration)");
                valueToFind_X = scanner.nextLine();
            }
        }
        else
        {
            problemTypeID = 2;
            System.out.println("What value are you trying to find? (delta_X, initial_velocity_X, delta_Y, initial_velocity_Y, final_velocity_Y, time, acceleration)");
            valueToFind_XY = scanner.nextLine();

            while (!valueToFind_XY.equalsIgnoreCase(dX) && !valueToFind_XY.equalsIgnoreCase(vXI) && !valueToFind_XY.equalsIgnoreCase(dY) && !valueToFind_XY.equalsIgnoreCase(vYI) 
                && !valueToFind_XY.equalsIgnoreCase(vYF) && !valueToFind_XY.equalsIgnoreCase(t) && !valueToFind_XY.equalsIgnoreCase(acc))
            {
                System.out.println("Please enter a valid response (delta_X, initial_velocity_X, delta_Y, initial_velocity_Y, final_velocity_Y, time, acceleration)");
                valueToFind_XY = scanner.nextLine();
            }
        }
    }

    public Double changeCalcValue()
    {
        RoundAllValues();

        if (problemTypeID == 1)
        {
            if (valueToFind_X.equalsIgnoreCase("delta_X")) return calculatedValues.get(0);
            else if (valueToFind_X.equalsIgnoreCase("initial_velocity_X")) return calculatedValues.get(1);
            else if (valueToFind_X.equalsIgnoreCase("final_velocity_X")) return calculatedValues.get(2);
            else if (valueToFind_X.equalsIgnoreCase("time")) return calculatedValues.get(3);
            else return calculatedValues.get(4);
        }
        else
        {
            if (!error_1 && !error_2)
            {
                if (valueToFind_XY.equalsIgnoreCase("delta_X")) return calculatedValues.get(0);
                else if (valueToFind_XY.equalsIgnoreCase("initial_velocity_X")) return calculatedValues.get(1);
                else if (valueToFind_XY.equalsIgnoreCase("delta_Y")) return calculatedValues.get(2);
                else if (valueToFind_XY.equalsIgnoreCase("initial_velocity_Y")) return calculatedValues.get(3);
                else if (valueToFind_XY.equalsIgnoreCase("final_velocity_Y")) return calculatedValues.get(4);
                else if (valueToFind_XY.equalsIgnoreCase("time")) return calculatedValues.get(5);
                return -9.8;
            }

            if (valueToFind_XY.equalsIgnoreCase("delta_X"))
            {
                System.out.println("Unable to find the X Displacement or X Velocity. Not enought information given. Please input an X Displacement or X Velocity to solve problem.");
                getValueToFind(false);
            }
            else if (valueToFind_XY.equalsIgnoreCase("initial_velocity_X"))
            {
                System.out.println("Unable to find the X Displacement or X Velocity. Not enought information given. Please input an X Displacement or X Velocity to solve problem.");
                getValueToFind(false);
            }
            else if (valueToFind_XY.equalsIgnoreCase("delta_Y")) return calculatedValues.get(2);
            else if (valueToFind_XY.equalsIgnoreCase("initial_velocity_Y")) return calculatedValues.get(3);
            else if (valueToFind_XY.equalsIgnoreCase("final_velocity_Y")) return calculatedValues.get(4);
            else if (valueToFind_XY.equalsIgnoreCase("time")) return calculatedValues.get(5);
            return -9.8;
        }
    }

    private void RoundAllValues()
    {
        for (int i = 0; i < calculatedValues.size(); i++)
        {
            calculatedValues.set(i, calculatedValues.get(i) * 10000.0);
            if (calculatedValues.get(i) % 10 >= 5) calculatedValues.set(i, calculatedValues.get(i) + 10.0);
            calculatedValues.set(i, calculatedValues.get(i) / 10.0);
            calculatedValues.set(i, 0.0 + Math.round(calculatedValues.get(i)));
            calculatedValues.set(i, calculatedValues.get(i) / 1000.0);
        }
    }
}