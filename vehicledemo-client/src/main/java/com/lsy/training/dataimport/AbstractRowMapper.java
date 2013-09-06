package com.lsy.training.dataimport;

import com.lsy.training.dto.Dto;
import com.lsy.training.dto.VendorDto;

/**
 * Created with IntelliJ IDEA.
 * User: marcus
 * Date: 15.05.13
 * Time: 05:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRowMapper<T extends Dto> {
    public abstract T mapRow(String[] row);

    public static class BadRowException extends RuntimeException {
        private static final long serialVersionUID = 112L;
    }

    public static class BadColumnException extends RuntimeException {
        private static final long serialVersionUID = 8451617922880948690L;
        String[] row;
        int failedCol;
        public BadColumnException(String[] row, int failedCol) {
            super();
            this.row = row;
            this.failedCol = failedCol;
        }
    }
}
