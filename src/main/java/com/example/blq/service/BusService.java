package com.example.blq.service;

import com.example.blq.mapper.BusMapper;
import com.example.blq.pojo.Bus;
import com.example.blq.pojo.Bus2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusMapper busMapper;

    public List getLineInfo(String city, String busType, String busKeyWords) {

        return busMapper.getLineInfo(city,busType,busKeyWords);
    }

    public int addLineInfo(Bus bus) {
        return busMapper.addLineInfo(bus);
    }

    public int updateLineInfo(Bus2 bus) {
        return busMapper.updateLineInfo(bus);
    }

    public List getBusLineOrStationInfo(String msg,String name) {
        return busMapper.getBusLineOrStationInfo(msg,name);
    }

    public List getDetailInfo(String name) {
        return busMapper.getDetailInfo(name);
    }





    public Bus2 checkBusLineIfExit(String id) {
        Bus2 bus = busMapper.checkBusLineIfExit(id);
        return bus;
    }

    public int addStationInfo(List list) {
        int result = busMapper.addStationInfo(list);
        return result;
    }
    public int addCityInfo(String adcode, String name, String level,String value,String parentCode){
        int result = busMapper.addCityInfo(adcode,name,level,value,parentCode);
        return result;
    }
    public String[] getProvince(){
        String[] result = busMapper.getProvince();
        return result;
    }
    public List getCityInfo(String name){
        List result = busMapper.getCityInfo(name);
        return result;
    }
    public int deleteInfo(String id){
        return busMapper.deleteInfo(id);
    }


}