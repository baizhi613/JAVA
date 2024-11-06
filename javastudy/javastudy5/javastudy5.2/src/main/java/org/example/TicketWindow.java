package org.example;

import java.util.Iterator;
import java.util.Map;

public class TicketWindow implements Runnable{
    private Map<String,Boolean> ticketMap;
    private String salerName;
    public TicketWindow(Map<String,Boolean> ticketMap,String salerName){
        this.ticketMap=ticketMap;
        this.salerName=salerName;
    }

    public void saleTicket(){
        for(Iterator<String> it=ticketMap.keySet().iterator();;){
            synchronized (ticketMap){
                if(it.hasNext()){
                    String ticketNo=it.next();
                    if(!ticketMap.get(ticketNo)){
                        System.out.println(salerName+":"+ticketNo+"已售出");
                        ticketMap.put(ticketNo,true);
                        try{
                            Thread.sleep(100);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    break;
                }
            }
        }
    }
    @Override
    public void run(){
        saleTicket();
    }
}