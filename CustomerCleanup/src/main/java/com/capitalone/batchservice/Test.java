package com.capitalone.batchservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        String jsonString="{\n" +
                "  \"customerPhoneNumbers\": [\n" +
                "    {\n" +
                "      \"isPrimary\": true,\n" +
                "      \"phoneType\": \"mobile\",\n" +
                "      \"phoneNumber\": \"8044022631\",\n" +
                "      \"timeUpdated\": \"2018-09-29T13:09:11.558Z\",\n" +
                "     \n" +
                "      \"addedTimestamp\": \"2018-09-29T13:09:11.558Z\",\n" +
                "      \"hasTcpaConsent\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"isPrimary\": false,\n" +
                "      \"phoneType\": \"home\",\n" +
                "      \"phoneNumber\": \"8048143874\",\n" +
                "      \"timeUpdated\": \"2018-09-29T13:09:11.558Z\",\n" +
                "      \"addedTimestamp\": \"2018-09-28T21:36:46.643Z\",\n" +
                "      \"hasTcpaConsent\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"isPrimary\": false,\n" +
                "      \"phoneType\": \"work\",\n" +
                "      \"phoneNumber\": \"8043632797\",\n" +
                "      \"timeUpdated\": \"2018-09-29T13:09:11.558Z\",\n" +
                "      \"addedTimestamp\": \"2018-09-29T13:09:11.558Z\",\n" +
                "      \"hasTcpaConsent\": true\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" +
                "\n";

        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            //convert JSON string to Map
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, String>>() {});
            List<Map<String,Object>> list= (List<Map<String,Object>>) map.get("customerPhoneNumbers");
            for(Map<String,Object> stringObjectMap:list){
                if(stringObjectMap.get("phoneNumber").toString().equals("8044022631")){
                    if((Boolean) stringObjectMap.get("isPrimary")){
                        checkAnotherPrimary(stringObjectMap,list);
                        //other process as it is
                    }
                }
            }
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkAnotherPrimary(Map<String, Object> stringObjectMap, List<Map<String, Object>> list) {
        List<Map> listOFPrimaryMap=new ArrayList<>();
        for(Map<String,Object> map:list){
            if((Boolean) map.get("isPrimary") && !map.get("phoneNumber").toString().equals(stringObjectMap.get("phoneNumber").toString())){
                listOFPrimaryMap.add(map);
            }
        }
        listOFPrimaryMap.forEach(map -> {
                boolean homeExist = listOFPrimaryMap.stream().anyMatch(item -> "home".equals(item.get("home")));
            if(homeExist){
                //get result and update
            }
            else{

            }
        });
//      listOFPrimaryMap.stream().map().entrySet().stream()
//              .filter(entry -> entry.getKey().equals(groupName))
//              .map(entry -> entry.getValue())
//              .flatMap(List::stream)
//              // as an option to replace the previous two
//              // .flatMap(entry -> entry.getValue().stream())
//              .collect(Collectors.toList());
    }

}
