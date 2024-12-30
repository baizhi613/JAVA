package org.baizhi.service;

import org.baizhi.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord getStockRecordById(Integer stockRecordId);
    List<StockRecord> getAllStockRecords();
    List<StockRecord> getStockRecordsByProductId(Integer productId);
    int createStockRecord(StockRecord stockRecord);
    int deleteStockRecord(Integer stockRecordId);
    int deleteStockRecordsByProductId(Integer productId);
} 