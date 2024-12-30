package org.baizhi.mapper;

import org.baizhi.model.StockRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StockRecordMapper {
    @Select("SELECT sr.*, p.productName FROM stockrecords sr LEFT JOIN products p ON sr.productId = p.productId")
    List<StockRecord> getAllStockRecords();

    @Select("SELECT sr.*, p.productName FROM stockrecords sr LEFT JOIN products p ON sr.productId = p.productId WHERE sr.stockRecordId = #{stockRecordId}")
    StockRecord getStockRecordById(Integer stockRecordId);

    @Select("SELECT sr.*, p.productName FROM stockrecords sr LEFT JOIN products p ON sr.productId = p.productId WHERE sr.productId = #{productId}")
    List<StockRecord> getStockRecordsByProductId(Integer productId);

    @Insert("INSERT INTO stockrecords (productId, type, quantity, note, date) " +
            "VALUES (#{productId}, #{type}, #{quantity}, #{note}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "stockRecordId")
    int insertStockRecord(StockRecord stockRecord);

    @Delete("DELETE FROM stockrecords WHERE stockRecordId = #{stockRecordId}")
    int deleteStockRecord(Integer stockRecordId);

    @Delete("DELETE FROM stockrecords WHERE productId = #{productId}")
    int deleteStockRecordsByProductId(Integer productId);
} 