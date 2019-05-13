package com.example.blq.mapper;


import com.example.blq.pojo.Bus;
import com.example.blq.pojo.Bus2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusMapper {
    List getLineInfo(@Param("city") String city,@Param("busType") String busType, @Param("busKeyWords") String busKeyWords);
    int addLineInfo(Bus bus);
    int updateLineInfo(Bus2 bus);
    List getBusLineOrStationInfo(@Param("msg") String msg,@Param("name") String name);
    List getDetailInfo(String name);
    Bus2 checkBusLineIfExit(String id);
    int addStationInfo(List list);
    int addCityInfo(@Param("adcode") String adcode, @Param("name") String name, @Param("level") String level,
                    @Param("value") String value,@Param("parentCode")String parentCode);
    String[] getProvince();
    List getCityInfo(@Param("name") String name);
    int deleteInfo(@Param("id") String id);
}
