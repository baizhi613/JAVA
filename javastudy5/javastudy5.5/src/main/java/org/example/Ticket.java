package org.example;

public class Ticket {
    String TrainNum;//车次,如G65
    String from;//出发站,如Beijing
    String to;//达到站,如Shanghai
    int carriage;//车厢,如第2车厢
    int seat;//座位,如25号座
    double price;//价格

    public Ticket(String trainNum, String from, String to, int carriage, int seat, double price) {
        TrainNum = trainNum;
        this.from = from;
        this.to = to;
        this.carriage = carriage;
        this.seat = seat;
        this.price = price;
    }

    public Ticket() {

    }

    public String getTrainNum() {
        return TrainNum;
    }

    public void setTrainNum(String trainNum) {
        TrainNum = trainNum;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}