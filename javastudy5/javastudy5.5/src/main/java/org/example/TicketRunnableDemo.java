package org.example;

import java.util.Hashtable;
public class TicketRunnableDemo {
    public static Hashtable<Ticket, Boolean> initTicket(String from, String to, String TrainNO, double price, int size) {
        Hashtable<Ticket, Boolean> ticketMap = new Hashtable<>();
        int mod = size / 10;
        for (int i = 0; i < size; i++) {
            Ticket t = new Ticket();
            t.from = from;
            t.to = to;
            t.price = price;
            t.TrainNum = TrainNO;
            t.carriage = Math.round(i / mod) + 1;
            t.seat = i % mod + 1;
            ticketMap.put(t, false);
        }
        return ticketMap;
    }

    public static void main(String[] args) {
        Hashtable<Ticket, Boolean> ticketMap = initTicket("Beijin", "Shanhai", "G65", 550, 250);
        TicketRunnable tr = new TicketRunnable(ticketMap);
        Thread t1 = new Thread(tr, "Window 1");
        Thread t2 = new Thread(tr, "Window 2");
        Thread t3 = new Thread(tr, "Window 3");
        Ticket ticket = ticketMap.keySet().iterator().next();
        System.out.println("从【" + ticket.from + "】开往【" + ticket.to + "】的列 车" + ticket.TrainNum + "即将开始售票......");
        System.out.println("本次共发行" + ticketMap.keySet().size() + "张车票");
        System.out.println("================================================");
        t1.start();
        t2.start();
        t3.start();
    }
}