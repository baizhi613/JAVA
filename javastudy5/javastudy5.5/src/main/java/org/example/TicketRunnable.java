package org.example;

import java.util.*;

public class TicketRunnable implements Runnable {
    Hashtable<Ticket, Boolean> ticketMap;//线程安全,效率低
    String window;

    public TicketRunnable(Hashtable<Ticket, Boolean> ticketMap) {
        this.ticketMap = ticketMap;
    }

    public TicketRunnable(Hashtable<Ticket, Boolean> ticketMap, String window) {
        this.ticketMap = ticketMap;
        this.window = window;
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = null;
            synchronized (ticketMap) {
                Iterator<Ticket> it = ticketMap.keySet().iterator();
                if (it.hasNext()) {
                    ticket = it.next();
                    ticketMap.remove(ticket);
                }
            }

            if (ticket != null) {
                System.out.println(window + " 售出票 " + ticket);
                try {
                    Thread.sleep(100); // 模拟售票时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break; // 没有票了，退出循环
            }
        }
    }

}