package com.lsy.training.dataimport;

import com.lsy.training.dto.VendorDto;

public class VendorRowMapper extends AbstractRowMapper<VendorDto> {

    protected static final int numberOfCols=1;

    public VendorDto mapRow(String[] row) {
        if (row == null || row.length != numberOfCols) {
            throw new BadRowException();
        }

        int i = 0;

        for (String col : row) {
            if (col == null || col.trim().isEmpty()) {
                throw new BadColumnException(row, i);
            }
            ++i;
        }

        return new VendorDto(row[0]);
    }

}
