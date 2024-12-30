package org.baizhi.service.serviceImpl;

import org.baizhi.mapper.StockRecordMapper;
import org.baizhi.model.StockRecord;
import org.baizhi.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public StockRecord getStockRecordById(Integer stockRecordId) {
        return stockRecordMapper.getStockRecordById(stockRecordId);
    }

    @Override
    public List<StockRecord> getAllStockRecords() {
        return stockRecordMapper.getAllStockRecords();
    }

    @Override
    public List<StockRecord> getStockRecordsByProductId(Integer productId) {
        return stockRecordMapper.getStockRecordsByProductId(productId);
    }

    @Override
    public int createStockRecord(StockRecord stockRecord) {
        return stockRecordMapper.insertStockRecord(stockRecord);
    }

    @Override
    public int deleteStockRecord(Integer stockRecordId) {
        return stockRecordMapper.deleteStockRecord(stockRecordId);
    }

    @Override
    public int deleteStockRecordsByProductId(Integer productId) {
        return stockRecordMapper.deleteStockRecordsByProductId(productId);
    }
} 