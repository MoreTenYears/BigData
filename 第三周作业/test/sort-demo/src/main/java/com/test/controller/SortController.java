package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SortController {

    /****
     * http://localhost:18081/sort
     */
    @RequestMapping(value = "/sort")
    public List<Integer> list() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(1);
        list.add(5);
        return sort(list);

    }

    private List<Integer> sort(List<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                Integer integer = list.get(j);
                Integer integer1 = list.get(j + 1);
                if (integer > integer1) {
                    list.set(j, integer1);
                    list.set(j + 1, integer);
                }
            }
        }

        return list;
    }

}
