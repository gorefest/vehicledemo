package com.lsy.training.dataimport;

import com.lsy.training.dto.Dto;
import com.lsy.training.dto.VendorDto;

import java.io.File;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marcus
 * Date: 15.05.13
 * Time: 05:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractImporter<T extends Dto> {
    AbstractRowMapper<T> rowMapper;
    FileScroller fileScroller;File file;

    public AbstractImporter() {
        super();
    }

    public void performImport() {

        fileScroller.open(file);

        String[] row;

        while ((row = fileScroller.next()) != null) {
            T dto = rowMapper.mapRow(row);
            Long id = saveDto(dto);
            System.out.println(id);
        }

        fileScroller.close();
    }

    public abstract Long saveDto(T dto);
}
