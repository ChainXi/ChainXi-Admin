package com.chainxi.system.component;

import com.chainxi.common.web.component.ContextLoader;
import com.chainxi.system.service.dict.DictDataService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DictCacheManager {
    private DictCacheManager() {
        dictDataService = ContextLoader.getBean(DictDataService.class);
    }

    private static volatile DictCacheManager INSTANCE;

    public static DictCacheManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DictCacheManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DictCacheManager();
                }
            }
        }
        return INSTANCE;
    }

    private final DictDataService dictDataService;

    @SneakyThrows
    public String getDictDataLabel(String dictType, Integer value) {
        return dictDataService.getDictData(dictType, String.valueOf(value)).getLabel();
    }

    @SneakyThrows
    public String getDictDataLabel(String dictType, String value) {
        return dictDataService.getDictData(dictType, value).getLabel();
    }

    @SneakyThrows
    public String parseDictDataValue(String dictType, String label) {
        return dictDataService.parseDictData(dictType, label).getLabel();
    }

}
