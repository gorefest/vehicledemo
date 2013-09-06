package com.lsy.training.dataimport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.lsy.training.dto.VehicleDto;

public class VehicleRowMapper extends AbstractRowMapper<VehicleDto>{

	DecimalFormat df = new DecimalFormat("##0.00");
	
	private static final int numberOfCols = 9;
	private static final int ccmCol 		= 5;
	private static final int cylCol 		= 4;
	private static final int kwCol 		= 7;
	private static final int priceCol	= 8;
	

	@Override
	public VehicleDto mapRow(String[] row) {
		int i = 0;
		Double kw = null;
		BigDecimal price = null; 
		Integer ccm = null;
		Integer cyl = null;
		
		if (row == null || row.length != numberOfCols) {
			throw new BadRowException();
		}
		
		for (String col : row) {
			if (col == null || col.trim().isEmpty()) {
				throw new BadColumnException(row, i);
			}
			try {
				switch (i) {
				case ccmCol:
					ccm = Integer.valueOf(col);
					break;
				case cylCol:
					cyl = Integer.valueOf(col);
					break;
				case kwCol:
					kw = Double.valueOf(df.parse(col).doubleValue());
					break;
				case priceCol: 
					price  = BigDecimal.valueOf(df.parse(col).doubleValue());
					break;
				}
			} catch (ParseException | NumberFormatException e) {
				throw new BadColumnException(row, i);
				
			}
			++i;			
		}

		return new VehicleDto(row[0], row[1], row[2], row[3], cyl, ccm, row[6], kw, price);
	}
}
