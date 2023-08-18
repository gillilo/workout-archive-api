package com.strmanager.api.utils;

import com.strmanager.api.domain.Gender;
import com.strmanager.api.domain.Member;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class WilksPoint {

    public double Coeff(Member member){
        DecimalFormat df = new DecimalFormat("0.##");
        double x = member.getWeight();
        double bp1rm = member.getPerformance().getBenchpress();
        double dl1rm = member.getPerformance().getDeadlift();
        double sq1rm = member.getPerformance().getSquat();
        double total = bp1rm + dl1rm + sq1rm;

        if(member.getGender() == Gender.MALE) {
            double maleA = -216.0475144;
            double maleB = 16.2606339;
            double maleC = -0.002388645;
            double maleD = -0.00113732;
            double maleE = 7.01863E-06;
            double maleF = -1.291E-08;

            String tmp = df.format(
                    total / (maleA + (maleB*x) + (maleC*x*x) + (maleD*x*x*x) +
                            (maleE*x*x*x*x) + (maleF*x*x*x*x*x))/2*1000
            );
            return Double.parseDouble(tmp);
        }
        else {
            double femaleA = 594.31747775582;
            double femaleB = -27.23842536447;
            double femaleC = 0.82112226871;
            double femaleD = -0.00930733913;
            double femaleE = 4.731582E-05;
            double femaleF = -9.054E-08;

            String tmp = df.format(
                    total / (femaleA + (femaleB*x) + (femaleC*x*x) + (femaleD*x*x*x) +
                            (femaleE*x*x*x*x) + (femaleF*x*x*x*x*x))/2*1000
            );
            return Double.parseDouble(tmp);
        }
    }
}
