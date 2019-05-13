package com.example.blq.controller;

import com.alibaba.fastjson.JSON;
import com.example.blq.pojo.Bus;
import com.example.blq.pojo.Bus2;
import com.example.blq.pojo.City;
import com.example.blq.pojo.Station;
import com.example.blq.service.BusService;
import com.example.blq.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class LineController {
    @Autowired
    private BusService busService;

    /**
     * 获取公交线路信息
     *
     * @return
     */
    @RequestMapping("/getLineInfo")
    public PageInfo<Bus2> getBusInfo(int pageSize, int currentPage, String city, String busType, String busKeyWords) {
        //  转化 busType2
        if (busType != "") {
            String[] busType2 = busType.split(",");
//        System.out.println(busType2[0]);
            String str = "";
            for (String a : busType2) {
                a = "'" + a + "'" + ",";
//                System.out.println(a);
                str = str + a;
            }
            busType = str.substring(0, str.length() - 1);
        }
//        System.out.println(city + "," + busType + "," + busKeyWords);
//        System.out.println(currentPage);
        PageHelper.startPage(currentPage,pageSize);
        List result = busService.getLineInfo(city, busType, busKeyWords);
        PageInfo<Bus2> pageInfo = new PageInfo<>(result);
        return pageInfo;
    }

    @RequestMapping("/addLineInfo")
    public boolean addLineInfo(@RequestBody Bus bus) {
        List<Station> list = new ArrayList<>();
        String via_sotps[] = bus.getVia_stops().split(",");
        String id = bus.getId();

        for (String a : via_sotps) {
            Station station = new Station();
            station.setId(UUIDUtil.getUUID());
            station.setName(a);
            station.setLineId(id);
            list.add(station);
        }
//        System.out.println(list);
        //判断是否存在
        Bus2 bus2 = busService.checkBusLineIfExit(bus.getId());
//        System.out.println(bus2);
        if (bus2 != null) {
            return false;
        }

        int result = busService.addLineInfo(bus);
        int result2 = busService.addStationInfo(list);

        if (result > 0 && result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/updateLineInfo")
    public boolean updateLineInfo(@RequestBody Bus2 bus) {
//        System.out.println(bus);
        int result = busService.updateLineInfo(bus);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/getBusLineOrStationInfo")
    public List getBusLineOrStationInfo(String msg,String city) {
     System.out.println(city);
        List result = busService.getBusLineOrStationInfo(msg,city);
        if (result.size() > 1) {
            return result;
        }

        return result;
    }

    @RequestMapping("/getDetailInfo")
    public List getDetailInfo(String name) {
//        System.out.println(name);
        List result = busService.getDetailInfo(name);
//        System.out.println(result);
        return result;
    }



    @RequestMapping("/addCityInfo")
    public void  addCityInfo(String adcode, String name, String level,String districtList){
//        System.out.println(adcode+","+name+","+level);
        if(adcode ==""){
            return;
        }
        busService.addCityInfo(adcode,name,level,"","");
        List<City> list = JSON.parseArray(districtList,City.class);
        for (City c:list){
            busService.addCityInfo(c.getAdcode(),c.getLabel(),c.getLevel(),c.getValue(),adcode);
        }
//        System.out.println(list);

    }
    @RequestMapping("/getProvince")
    public String[] getProvince(){
       String[] result  = busService.getProvince();
//        System.out.println(result);
            return result;
    }

    @RequestMapping("/getCityInfo")
    public List getCityInfo(String province){
///       System.out.println(province);
        List result  = busService.getCityInfo(province);
//        System.out.println(result);
        return result;
    }
    @RequestMapping("/deleteInfo")
    public boolean deleteInfo(String id){
     System.out.println(id);
        int result  = busService.deleteInfo(id);
        System.out.println(result);
        if(result>0){
            return  true;
        }else{
            return false;
        }

    }


}