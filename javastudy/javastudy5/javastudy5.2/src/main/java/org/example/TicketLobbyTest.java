package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketLobbyTest {
    public static void main(String[] args) {
        Map<String,Boolean> ticketMap=new HashMap<String,Boolean>();
        for(int i=1;i<1000;i++){
            ticketMap.put("T"+i,false);
        }
        TicketWindow s1=new TicketWindow(ticketMap,"张三");
        TicketWindow s2=new TicketWindow(ticketMap,"李四");
        TicketWindow s3=new TicketWindow(ticketMap,"王五");
        TicketWindow s4=new TicketWindow(ticketMap,"赵六");
        Thread t1=new Thread(s1);
        Thread t2=new Thread(s2);
        Thread t3=new Thread(s3);
        Thread t4=new Thread(s4);
        ExecutorService service= Executors.newCachedThreadPool();
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.shutdown();
    }
}
