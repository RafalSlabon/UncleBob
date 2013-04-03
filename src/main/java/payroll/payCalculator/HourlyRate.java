package payroll.payCalculator;


import payroll.TimeCard;

import java.util.ArrayList;
import java.util.List;

public class HourlyRate implements PayCalculator {
    private double rate;
    private List<TimeCard> timeCards = new ArrayList<TimeCard>();

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        return 0;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    public double getRate() {
        return rate;
    }
}
