package com.blfc.backend.Utils;


import java.util.LinkedHashMap;
import java.util.Map;


public class BaseUtil {



    public static Map<String, String> getAssocParams(Map<String, String> params) {
        Map<String, String> assocArray = new LinkedHashMap<String, String>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().startsWith("p[")) {
                String key = entry.getKey().substring(entry.getKey().indexOf('[') + 1, entry.getKey().indexOf(']'));
                System.out.println("Ini key: " +key );
                assocArray.put(key, entry.getValue());
            }
            System.out.println("Ini entry: " +entry );
        }

        System.out.println("Ini Assoc param : " +assocArray );
        return assocArray;
    }


    public static String getOrderBy(String order) {
        String orderBy = "id_channel ASC";
        if (order != null && !order.isEmpty()) {
            String[] parseOrder = order.split("-", 2);
            orderBy = parseOrder[0] + " " + parseOrder[1];
        }
        return orderBy;
    }
    
    public static String getClauseWhere(Map<String, String> params) {
        String where = "";
        if (params != null && !params.isEmpty()) {
            where += " WHERE ";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!where.equals(" WHERE ")) {
                    where += " AND ";
                }
                String[] parseKey = entry.getKey().split("_", 2);
                if (parseKey[1].equalsIgnoreCase("contains")) {
                    where += parseKey[0] + " LIKE '%" + entry.getValue() + "%' ";
                } else if (parseKey[1].equalsIgnoreCase("eq")) {
                    where += parseKey[0] + "='" + entry.getValue() + "' ";
                } else if (parseKey[1].equalsIgnoreCase("starts_with")) {
                    where += parseKey[0] + " LIKE '" + entry.getValue() + "%' ";
                } else if (parseKey[1].equalsIgnoreCase("ends_with")) {
                    where += parseKey[0] + " LIKE '%" + entry.getValue() + "' ";
                }
            }
        }
        return where;
    }

}
